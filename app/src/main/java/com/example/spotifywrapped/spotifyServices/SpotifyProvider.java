package com.example.spotifywrapped.spotifyServices;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

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
public class SpotifyProvider implements Serializable {

    private SpotifyAppRemote mSpotifyAppRemote;
    private static String accessToken;

    /**
     * Constructor that connects to the Spotify App Remote API and returns a SpotifyAppRemote object.
     *
     * @param clientID     The client ID for the Spotify App Remote connection.
     * @param redirectURI  The redirect URI for the Spotify App Remote connection.
     * @param accessToken  The access token for authenticating API requests.
     * @param context      The context of the Android application.
     */
    public SpotifyProvider(String clientID, String redirectURI, String accessToken, Context context) {
        // Set the connection parameters
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
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        Log.e("SpotifyProvider", throwable.getMessage(), throwable);
                    }
                });
        SpotifyProvider.accessToken = accessToken;
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
    private static class FetchTopTracksTask extends AsyncTask<Integer, Void, ArrayList<String>> {

        private OnTopTracksListener listener;

        public FetchTopTracksTask(OnTopTracksListener listener) {
            this.listener = listener;
        }

        @Override
        protected ArrayList<String> doInBackground(Integer... params) {
            int numTracks = params[0];
            int startPos = params[1];
            ArrayList<String> topTracks = new ArrayList<>();

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
                        topTracks.add(trackName);
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
        protected void onPostExecute(ArrayList<String> topTracks) {
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
        void onTopTracksReceived(ArrayList<String> topTracks);
    }

}