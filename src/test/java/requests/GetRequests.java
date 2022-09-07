package requests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;
import utilities.SetProperties;


public class GetRequests {


    //Get properties
    private final SetProperties prop = new SetProperties();
    private final String BASE_URL = prop.getBaseUrl();
    private String userId;

    //Get Current User's Profile: https://developer.spotify.com/documentation/web-api/reference/#/operations/get-current-users-profile
  public void getUserInformation(String token){

      Response resp = RestAssured
              .given()
                  .auth()
                  .oauth2(token)
              .when()
                  .get( BASE_URL+"/me")
              .then().extract().response();

      setUserId(resp.jsonPath().getString("id"));
  }

    public void validatePlaylistExist(String token, String PlaylistId){

        RestAssured
                .given()
                    .auth()
                        .oauth2(token)
                .when()
                    .get(BASE_URL+"/me/playlists")
                .then()
                    .statusCode(200)
                    .body("items.id", hasItem(PlaylistId));

    }

    public void validatePlaylistInformation(String token, String playlistId,String playlistName,String playlistPublic, String playlistCollab){
        RestAssured
                .given()
                    .auth()
                    .oauth2(token)
                .when()
                    .get(BASE_URL+"/playlists/"+playlistId)
                .then()
                    .statusCode(200)
                    .body("name",equalTo(playlistName))
                    .body("public",equalTo(Boolean.parseBoolean(playlistPublic)))
                    .body("collaborative", equalTo(Boolean.parseBoolean(playlistCollab)));
    }

    public void validateTracks(String token, String playlistId,String trackUri,String trackName){
        RestAssured
                .given()
                    .auth()
                    .oauth2(token)
                .when()
                    .get(BASE_URL+"/playlists/"+playlistId+"/tracks")
                .then()
                    .statusCode(200)
                    .body("items.track.uri", hasItem(trackUri))
                    .body("items.track.name",hasItem(trackName));
    }



    //getters and setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
