package com.example.spotifywrapped.spotifyServices;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.spotifywrapped.models.Artist;
import com.example.spotifywrapped.models.Track;
import com.spotify.android.appremote.api.SpotifyAppRemote;
import com.spotify.android.appremote.api.ConnectionParams;
import com.spotify.android.appremote.api.Connector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;

/**
 * This class allows connecting with the Spotify API and deals with retrieving
 * and modifying Spotify information and playing music in-app.
 */
public class SpotifyProvider {

    private static SpotifyProvider instance; // Singleton instance
    private SpotifyAppRemote mSpotifyAppRemote;
    private static String accessToken;
    private SpotifyProviderListener mListener;

    // Private constructor to prevent direct instantiation
    private SpotifyProvider(String clientID, String redirectURI, String accessToken, Context context, SpotifyProviderListener listener) {
        this.mListener = listener;
        // Initialization tasks here
        ConnectionParams connectionParams =
                new ConnectionParams.Builder(clientID)
                        .setRedirectUri(redirectURI)
                        .showAuthView(true)
                        .build();
        SpotifyAppRemote.connect(context, connectionParams,
                new Connector.ConnectionListener() {

                    @Override
                    public void onConnected(SpotifyAppRemote spotifyAppRemote) {
                        mSpotifyAppRemote = spotifyAppRemote;
                        Log.d("SpotifyProvider", "Connected! Yay!");
                        if (mListener != null) {
                            mListener.onSpotifyProviderInitialized(SpotifyProvider.this);
                        }
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        Log.e("SpotifyProvider", throwable.getMessage(), throwable);
                        if (mListener != null) {
                            mListener.onSpotifyProviderInitialized(SpotifyProvider.this);
                        }
                    }
                });
        SpotifyProvider.accessToken = accessToken;
    }

    // Static method to initialize the Singleton instance
    public static synchronized void initialize(String clientID, String redirectURI, String accessToken, Context context, SpotifyProviderListener listener) {
        instance = new SpotifyProvider(clientID, redirectURI, accessToken, context, listener);
    }

    // Static method to get the Singleton instance
    public static synchronized SpotifyProvider getInstance() {
        return instance;
    }

    /**
     * @return whether the provider object is valid
     */
    public boolean isValid() {
        return mSpotifyAppRemote != null;
    }

    /**
     * Interface to listen for SpotifyProvider initialization events.
     */
    public interface SpotifyProviderListener {
        /**
         * Called when the SpotifyProvider is initialized and connected.
         *
         * @param spotifyProvider The initialized SpotifyProvider object.
         */
        void onSpotifyProviderInitialized(SpotifyProvider spotifyProvider);
    }

    /**
     * Retrieves the top tracks from the Spotify API asynchronously and notifies the listener
     * when the operation is complete.
     *
     * @param numTracks The number of top tracks to retrieve.
     * @param startPos  The starting position for retrieving top tracks.
     * @param listener  The listener to be notified when the top tracks are received.
     */
    public void getTopTracks(int numTracks, int startPos, OnTopTracksListener listener) {
        new FetchTopTracksTask(listener).execute(numTracks, startPos);
    }

    /**
     * Asynchronous task to fetch the top tracks from the Spotify API.
     */
    private static class FetchTopTracksTask extends AsyncTask<Integer, Void, ArrayList<Track>> {

        private OnTopTracksListener listener;

        public FetchTopTracksTask(OnTopTracksListener listener) {
            this.listener = listener;
        }

        @Override
        protected ArrayList<Track> doInBackground(Integer... params) {
            int numTracks = params[0];
            int startPos = params[1];
            ArrayList<Track> topTracks = new ArrayList<>();

            try {
                URL url = new URL(String.format(Locale.ENGLISH, "https://api.spotify.com/v1/me/top/tracks?limit=%d&offset=%d", numTracks, startPos));
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("Authorization", "Bearer " + accessToken);
                int responseCode = con.getResponseCode();
                System.out.println("GET Response Code :: " + responseCode);
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuilder response = new StringBuilder();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    // Parse the response and add to topTracks ArrayList
                    JSONObject jsonResponse = new JSONObject(response.toString());
                    JSONArray items = jsonResponse.getJSONArray("items");
                    for (int i = 0; i < items.length(); i++) {
                        String trackName = items.getJSONObject(i).getString("name");
                        String image = items.getJSONObject(i).getJSONObject("album").getJSONArray("images").getJSONObject(0).getString("url");
                        long length = items.getJSONObject(i).getInt("duration_ms");
                        int popularity = items.getJSONObject(i).getInt("popularity");
                        String uri = items.getJSONObject(i).getString("uri");
                        String previewUrl = items.getJSONObject(i).getString("preview_url");
                        ArrayList<String> artists = new ArrayList<>();
                        JSONArray artistList = items.getJSONObject(i).getJSONArray("artists");
                        for (int j = 0; j < artistList.length(); j++) {
                            String artistName = artistList.getJSONObject(j).getString("name");
                            artists.add(artistName);
                        }
                        topTracks.add(new Track(artists, length, uri, trackName, popularity, image, previewUrl));
                    }

                } else {
                    Log.d("SpotifyProvider", "GET request did not work");
                }
            } catch (IOException e) {
                Log.d("SpotifyProvider", e.getMessage());
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

            return topTracks;
        }

        @Override
        protected void onPostExecute(ArrayList<Track> topTracks) {
            if (listener != null) {
                listener.onTopTracksReceived(topTracks);
            }
        }
    }

    /**
     * Interface to listen for top tracks retrieval events.
     */
    public interface OnTopTracksListener {
        /**
         * Called when the top tracks are received.
         *
         * @param topTracks The list of top tracks.
         */
        void onTopTracksReceived(ArrayList<Track> topTracks);
    }

    /**
     * Retrieves the top artists from the Spotify API asynchronously and notifies the listener
     * when the operation is complete.
     *
     * @param numArtists The number of top artists to retrieve.
     * @param startPos   The starting position for retrieving top artists.
     * @param listener   The listener to be notified when the top artists are received.
     */
    public void getTopArtists(int numArtists, int startPos, OnTopArtistsListener listener) {
        new FetchTopArtistsTask(listener).execute(numArtists, startPos);
    }

    /**
     * Asynchronous task to fetch the top artists from the Spotify API.
     */
    private static class FetchTopArtistsTask extends AsyncTask<Integer, Void, ArrayList<Artist>> {

        private OnTopArtistsListener listener;

        public FetchTopArtistsTask(OnTopArtistsListener listener) {
            this.listener = listener;
        }

        @Override
        protected ArrayList<Artist> doInBackground(Integer... params) {
            int numArtists = params[0];
            int startPos = params[1];
            ArrayList<Artist> topArtists = new ArrayList<>();

            try {
                URL url = new URL(String.format(Locale.ENGLISH, "https://api.spotify.com/v1/me/top/artists?limit=%d&offset=%d", numArtists, startPos));
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("Authorization", "Bearer " + accessToken);
                int responseCode = con.getResponseCode();
                System.out.println("GET Response Code :: " + responseCode);
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuilder response = new StringBuilder();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    // Parse the response and add to topArtists ArrayList
                    JSONObject jsonResponse = new JSONObject(response.toString());
                    JSONArray items = jsonResponse.getJSONArray("items");
                    for (int i = 0; i < items.length(); i++) {
                        String artistName = items.getJSONObject(i).getString("name");
                        String image = items.getJSONObject(i).getJSONArray("images").getJSONObject(0).getString("url");
                        int popularity = items.getJSONObject(i).getInt("popularity");
                        ArrayList<String> genres = new ArrayList<>();
                        JSONArray genreList = items.getJSONObject(i).getJSONArray("genres");
                        for (int j = 0; j < genreList.length(); j++) {
                            String genre = genreList.getString(j);
                            genres.add(genre);
                        }
                        topArtists.add(new Artist(artistName, genres, popularity, image));
                    }

                } else {
                    Log.d("SpotifyProvider", "GET request did not work");
                }
            } catch (IOException e) {
                Log.d("SpotifyProvider", e.getMessage());
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

            return topArtists;
        }

        @Override
        protected void onPostExecute(ArrayList<Artist> topArtists) {
            if (listener != null) {
                listener.onTopArtistsReceived(topArtists);
            }
        }
    }

    /**
     * Interface to listen for top artists retrieval events.
     */
    public interface OnTopArtistsListener {
        /**
         * Called when the top artists are received.
         *
         * @param topArtists The list of top artists.
         */
        void onTopArtistsReceived(ArrayList<Artist> topArtists);
    }

}
