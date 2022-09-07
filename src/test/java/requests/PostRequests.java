package requests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.awaitility.Awaitility;
import org.json.JSONObject;
import utilities.SetProperties;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

public class PostRequests {

    //Get properties
    private final SetProperties prop = new SetProperties();

    private final String CLIENT_ID = prop.getClientId();
    private final String CLIENT_SECRET = prop.getClientSecret();
    private final String BASE_URL = prop.getBaseUrl();
    private final String TOKEN_URL = prop.getTokenUrl();
    private final JSONObject json = new JSONObject();
    private final Collection<String> collection = new ArrayList<>();
    private String accessToken;
    private String idPlaylist;


    //Encode in Base64 the authentication information to create the token
    String auth = "Basic " + Base64.getEncoder().encodeToString((CLIENT_ID+":"+CLIENT_SECRET).getBytes());

    //Create a new Authorization token: https://developer.spotify.com/documentation/general/guides/authorization/code-flow/
    public void createToken(String code){

        Response resp = RestAssured
                .given()
                    .headers("Authorization",auth,"Content-Type","application/x-www-form-urlencoded")
                    .params("grant_type","authorization_code","code",code,"redirect_uri","https://developer.spotify.com/")
                .when()
                    .post(TOKEN_URL)
                .then().extract().response();

        setAccessToken(resp.jsonPath().getString("access_token"));

    }

    //Create Playlist: https://developer.spotify.com/documentation/web-api/reference/#/operations/create-playlist
    public void createPlaylist(String token, String userId, String playlistName, String playlistPublic, String playlistCollab){

        //Create json (payload)
        json.put("name",playlistName);
        json.put("public",playlistPublic);
        json.put("collaborative",playlistCollab);

        Awaitility.await().atMost(10, TimeUnit.SECONDS).pollInterval(1, TimeUnit.SECONDS).until(() ->
                {
                    Response resp =  RestAssured
                            .given()
                                .auth()
                                .oauth2(token)
                                .body(json.toString())
                            .when()
                                .post(BASE_URL+"/users/"+userId+"/playlists")
                            .then()
                                .statusCode(201)
                                .extract().response();
                    setIdPlaylist(resp.jsonPath().getString("id"));

                    final boolean status = String.valueOf(resp.getStatusCode()).equals("201");
                    return status;
                });



    }


    //Add Items to Playlist: https://developer.spotify.com/documentation/web-api/reference/#/operations/add-tracks-to-playlist
    public void addItemPlaylist(String token,String playlistId,String uri){

        //Create json (payload)
        collection.add(uri);
        json.put("uris",collection);

        Awaitility.await().atMost(10, TimeUnit.SECONDS).pollInterval(1, TimeUnit.SECONDS).until(() ->
        {
            Response resp = RestAssured
                    .given()
                        .auth()
                        .oauth2(token)
                        .body(json.toString())
                    .when()
                        .post(BASE_URL+"/playlists/"+playlistId+"/tracks")
                    .then()
                        .statusCode(201).extract().response();

            final boolean status = String.valueOf(resp.getStatusCode()).equals("201");
            return status;
        });



    }

    //getters and setters
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getIdPlaylist() {
        return idPlaylist;
    }

    public void setIdPlaylist(String idPlaylist) {
        this.idPlaylist = idPlaylist;
    }
}
