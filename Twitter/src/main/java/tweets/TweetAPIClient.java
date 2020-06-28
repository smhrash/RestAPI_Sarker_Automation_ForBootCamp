package tweets;

import base.TwitterAPIClient;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class TweetAPIClient extends TwitterAPIClient {


    private final String CREATE_TWEET_ENDPOINT="/statuses/update.json";
    private final String DELETE_TWEET_ENDPOINT="/statuses/destroy.json";
    private final String GET_USER_TWEETS_ENDPOINT="/statuses/user_timeline.json";


    public ValidatableResponse getUserTimeLineTweets(){


        return given().auth().oauth(this.apiKey,this.apiSecretKey,this.accessToken,this.accessTokenSecret)
                .when().get(this.baseUri +this.GET_USER_TWEETS_ENDPOINT)
                .then();
    }
    // create a tweet from user's twitter

    public ValidatableResponse createTweet(String tweet){

        return given().auth().oauth(this.apiKey,this.apiSecretKey,this.accessToken,this.accessTokenSecret)
                .param("status",tweet)
                .when().post(this.baseUri +this.CREATE_TWEET_ENDPOINT)
                .then();

    }
    // delete a tweet from user tweet

    public ValidatableResponse deleteTweet(Long tweetId){
        return given().auth().oauth(this.apiKey,this.apiSecretKey,this.accessToken,this.accessTokenSecret)
                .queryParam("id",tweetId)
                .when().post(this.baseUri +this.DELETE_TWEET_ENDPOINT)
                .then();


    }


}
