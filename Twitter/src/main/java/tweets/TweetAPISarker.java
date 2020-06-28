package tweets;

import base.TwitterAPIClient;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class TweetAPISarker extends TwitterAPIClient {

    private final String SEARCH_TWEETS_ENDPOINT = "/search/tweets.json";
    private final String GET_AVAILABLE_TRENDS_ENDPOINT = "/trends/available.json";
    private final String GET_PLACES_NEAR_ENDPOINT = "/geo/search.json";
    private final String USERS_LOOK_UP_ENDPOINT = "/users/lookup.json";
    private final String FOLLOW_USER_ENDPOINT = "/users/lookup.json";
    private final String GET_FAVORITES_ENDPOINT = "/favorites/list.json";
    private final String CREATE_TWEET_ENDPOINT = "/statuses/update.json";
    private final String RETWEET_BY_ID_ENDPOINT = "/statuses/retweet/:id.json";
    private final String GET_TWEET_BY_ID_ENDPOINT = "/statuses/show.json";



    public ValidatableResponse searchTweets(String atUsername) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("q", atUsername)
                .when().get(this.baseUri + this.SEARCH_TWEETS_ENDPOINT)
                .then();
    }



    public ValidatableResponse getFavorites(String user_id) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("user_id", user_id)
                .when().get(this.baseUri + this.GET_FAVORITES_ENDPOINT)
                .then();
    }

    public ValidatableResponse getAvailableTrends() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUri + this.GET_AVAILABLE_TRENDS_ENDPOINT)
                .then();
    }

    // Create a tweet from user's twitter
    public ValidatableResponse createTweet(String tweet) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("status", tweet)
                .when().post(this.baseUri + this.CREATE_TWEET_ENDPOINT)
                .then();
    }

    public ValidatableResponse reTweet(String retweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("id", retweetId)
                .when().post(this.baseUri + this.RETWEET_BY_ID_ENDPOINT)
                .then();
    }


    public ValidatableResponse readTweet(String tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", tweetId)
                .when().get(this.baseUri + this.GET_TWEET_BY_ID_ENDPOINT)
                .then();
    }



    public ValidatableResponse getPlacesNear(String places) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("query", places)
                .when().get(this.baseUri + this.GET_PLACES_NEAR_ENDPOINT)
                .then();
    }



    public ValidatableResponse lookUpUsers(String user_id) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("user_id", user_id)
                .when().get(this.baseUri + this.USERS_LOOK_UP_ENDPOINT)
                .then();
    }
}
