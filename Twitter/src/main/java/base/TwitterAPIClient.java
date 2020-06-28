package base;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class TwitterAPIClient {
    protected String apiKey="";
    protected String apiSecretKey="";
    protected String accessToken="";
    protected String accessTokenSecret="";
    protected Properties properties;
    protected InputStream inputStream;
    protected String baseUri;

    public TwitterAPIClient(){
        this.baseUri ="http://api.tweitter.com/1.1";
        this.properties=new Properties();
        inputStream=null;




        try {

            this.inputStream=new FileInputStream("src/secret.properties");
            this.properties.load(this.inputStream);
            this.apiKey=this.properties.getProperty("apiKey");
            this.apiSecretKey=this.properties.getProperty("apiSecretKey");
            this.accessToken=this.properties.getProperty("accessToken");
            this.accessTokenSecret=this.properties.getProperty("accessTokenSecret");

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("file not found");

        }finally {
            try {
                this.inputStream.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }


    }
}
