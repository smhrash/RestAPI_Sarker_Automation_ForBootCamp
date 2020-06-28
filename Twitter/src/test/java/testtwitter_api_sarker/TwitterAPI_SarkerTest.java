package testtwitter_api_sarker;

import base.DataProviders;
import common.WebAPI;
import io.restassured.response.ResponseBody;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tweets.TweetAPISarker;

import java.util.UUID;

public class TwitterAPI_SarkerTest extends WebAPI {
    private TweetAPISarker tweetAPIClient;

    @BeforeClass
    public void setUpTweetAPI() {
        this.tweetAPIClient = new TweetAPISarker();
    }

    /** test Case 1
     * Verify that the API is able to retrieve Twitter search results when searching by @username
     */
    @Test(enabled = false, dataProvider = "Twitter usernames", dataProviderClass = DataProviders.class)
    public void testSearchTweets(String username) {
        loggerInfo();
        // set the desired username data
        String userId = "@" + username;
        // saerch Twitter for the desired username
        ValidatableResponse response = this.tweetAPIClient.searchTweets(userId);
        ResponseBody body = (ResponseBody) response.extract().body();
        System.out.println("Response Body is: " + body.asString());
        String json = response.extract().contentType();
        System.out.println(json);
        int actualResponseCode = response.extract().statusCode();
        int expectedResponseCode = 200;
        // assert that the request was successful
        Assert.assertEquals(actualResponseCode, expectedResponseCode);
    }

    /** Test Case 2
     * Verify that the API is able to retrieve av available trends
     */
    @Test(enabled = false)
    public void testTrends() {
        loggerInfo();
        ValidatableResponse response = this.tweetAPIClient.getAvailableTrends();
        ResponseBody body = (ResponseBody) response.extract().body();
        System.out.println("Response Body is: " + body.asString());
        String json = response.extract().contentType();
        System.out.println(json);
        int actualResponseCode = response.extract().statusCode();
        int expectedResponseCode = 200;
        Assert.assertEquals(actualResponseCode, expectedResponseCode);
    }

    /** Test Case 3
     * Verify that the API is able to return the list of favorites for the given user ID
     */
    @Test(enabled = false, dataProvider = "Twitter ids", dataProviderClass = DataProviders.class)
    public void testFavorites(String ids) {
        loggerInfo();
        ValidatableResponse response = this.tweetAPIClient.getFavorites(ids);
        ResponseBody body = (ResponseBody) response.extract().body();
        System.out.println("Response Body is: " + body.asString());
        String json = response.extract().contentType();
        System.out.println(json);
        int actualResponseCode = response.extract().statusCode();
        int expectedResponseCode = 200;
        Assert.assertEquals(actualResponseCode, expectedResponseCode);
    }

    /** Test Case 4
     * Verify that API is able to post to the twitter account
     */
    @Test(enabled = false)
    public void testUserCanTweetSuccessfully() {
        loggerInfo();
        // 1. User send a tweet
        String tweet = "Tweet " + UUID.randomUUID().toString();
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
        // Verify that the tweet was successfully send
        String actualTweet = response.extract().body().path("text");
        Assert.assertEquals(tweet, actualTweet);
    }

    /** test Case 5
     * Verify API is able to retweet to status
     */
    @Test(enabled = false, dataProviderClass = DataProviders.class, dataProvider = "Tweet ids")
    public void verifyReTweet(String tweetId) {
        loggerInfo();
        ValidatableResponse response = this.tweetAPIClient.reTweet(tweetId);
        ValidatableResponse responseExpected = this.tweetAPIClient.readTweet(tweetId);
        String actualTweet = response.extract().body().path("text");
        String expectedTweet = responseExpected.extract().body().path("text");
        System.out.println("Actual retweet text: " + actualTweet);
        System.out.println("Expected retweet text: " + expectedTweet);
        Assert.assertEquals(actualTweet, expectedTweet);
    }

    /** test Case 6
     * Verify API is able to retrieve the available places suggested based on specified location
     *  ! Does not work with the data provider because it produces too many requests
     *  too fast and returns code 429 - “Too Many Requests” - Otherwise the test is correct.
     */
    @Test(enabled = true, dataProvider = "Twitter places", dataProviderClass = DataProviders.class)
    public void verifyLocations(String city) {
        loggerInfo();
        ValidatableResponse response = this.tweetAPIClient.getPlacesNear(city);
        String actualPlace = response.extract().contentType();
        ResponseBody body = (ResponseBody) response.extract().body();
        System.out.println("Response Body is: " + body.asString());
        int actualResponseCode = response.extract().statusCode();
        int expectedResponseCode = 200;
        Assert.assertEquals(actualResponseCode, expectedResponseCode);
    }


    /** Test Case 7
     * Verify that the API is able to look up users with the given user ID
     */
    @Test(enabled = false, dataProvider = "Twitter ids", dataProviderClass = DataProviders.class)
    public void testLookUpUser(String ids) {
        loggerInfo();
        ValidatableResponse response = this.tweetAPIClient. lookUpUsers(ids);
        ResponseBody body = (ResponseBody) response.extract().body();
        System.out.println("Response Body is: " + body.asString());
        String json = response.extract().contentType();
        System.out.println(json);
        int actualResponseCode = response.extract().statusCode();
        int expectedResponseCode = 200;
        Assert.assertEquals(actualResponseCode, expectedResponseCode);
    }
}
