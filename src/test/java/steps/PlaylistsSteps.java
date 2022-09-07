package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import requests.GetRequests;
import requests.PostRequests;
import utilities.Auth;
import utilities.SetProperties;


public class PlaylistsSteps {

    private final PostRequests postRequests = new PostRequests();
    private final GetRequests getRequests = new GetRequests();
    private final SetProperties prop = new SetProperties();
    private final Auth auth = new Auth();
    private final String PLAYLIST_ID = "7nkDZ7Ef5WjdcSN2gk8eMo";
    private String playlistName;
    private String playlistPublic;
    private String playlistCollab;
    private String trackUri;
    private String trackName;

    //-------------------------- Background --------------------------
    @Given("^User is authenticated$")
    public void user_is_authenticated() {
        //Get Authorization code and create Access Token
        String authCode;
        authCode=auth.getAuthCode(prop.getUsername(), prop.getPassword(), prop.getClientId());
        postRequests.createToken(authCode);
    }

    @And("^User is the owner of the account$")
    public void user_is_the_owner_of_the_account() {
        //Execute get request to retrieve the current user id
        getRequests.getUserInformation(postRequests.getAccessToken());
    }


    //-------------------------- First Scenario --------------------------
    @When("^User creates a new playlist with ([^\"]*),([^\"]*) and ([^\"]*)$")
    public void user_creates_a_new_playlist_with_playlist_name_public_and_collaborative(String playlistName, String playlistPublic, String playlistCollab) {
        this.playlistName = playlistName;
        this.playlistPublic = playlistPublic;
        this.playlistCollab = playlistCollab;
        //Create a new Playlist
        postRequests.createPlaylist(postRequests.getAccessToken(), getRequests.getUserId(),playlistName,playlistPublic,playlistCollab);

    }

    @Then("^A new playlist is created$")
    public void a_new_playlist_is_created(){
        //Validate that the playlist was created
        getRequests.validatePlaylistExist(postRequests.getAccessToken() ,postRequests.getIdPlaylist());
        //Validate the data of the new playlist
        getRequests.validatePlaylistInformation(postRequests.getAccessToken(), postRequests.getIdPlaylist(),playlistName,playlistPublic,playlistCollab);
    }

    //-------------------------- Second Scenario --------------------------
    @Given("^User has a created playlist$")
    public void user_has_a_created_playlist() {
        //Validate that the playlist was created
        getRequests.validatePlaylistExist(postRequests.getAccessToken() ,PLAYLIST_ID);
    }

    @When("^User add a new track to the playlist with ([^\"]*) and ([^\"]*)$")
    public void user_add_a_new_track_to_the_playlist_with_song_name_and_track_uri(String trackName,String trackUri) {
        this.trackUri = trackUri;
        this.trackName = trackName;
        //Add a new track to the playlist
        postRequests.addItemPlaylist(postRequests.getAccessToken(), PLAYLIST_ID,trackUri);
    }

    @Then("^A new track is added to the playlist$")
    public void a_new_track_is_added_to_the_playlist() {
        //Validate the tracks were created
        getRequests.validateTracks(postRequests.getAccessToken(),PLAYLIST_ID,trackUri,trackName);
    }

}
