package testtweet;


import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tweets.TweetAPIClient;

import java.util.UUID;

public class TweetTest {


    private TweetAPIClient tweetAPIClient;


    @BeforeClass
    public void setUpTweetAPI() {

        this.tweetAPIClient = new TweetAPIClient();


    }

    @Test(enabled = true)
    public void testUserCanTweetSuccessfully() {
        // user send a tweet
        String tweet = "Tweet " + UUID.randomUUID().toString();
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
        // verify that tweet was successfully sent
        response.statusCode(200);
        String actualTweet = response.extract().body().path("text");

        Assert.assertEquals(actualTweet, tweet);
    }

    @Test(enabled = false)
    // write an API test where user can Tweet same tweet twice in a row
    public void testUserCanNotTweetSameTweetTwiceInARow() {
        //user send a tweet
        String tweet = "Tweet " + UUID.randomUUID().toString();
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
        // verify that tweet was successfully sent
        response.statusCode(200);
        String actualTweet = response.extract().body().path("text");

        Assert.assertEquals(actualTweet, tweet);
        // user send same tweet again
        response = this.tweetAPIClient.createTweet(tweet);
        // verify that tweet was unsuccessful
        response.statusCode(403);
        String expectedMessage = "Status is duplicate";
        String actualMessage = response.extract().body().path("errors[0].message");
        Assert.assertEquals(actualMessage, expectedMessage);
    }

}
