import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/features",
glue="steps",
//tags = "@CreatePlaylist"
//tags="@AddTrack"
tags="@Playlist"
)
public class Tests extends AbstractTestNGCucumberTests {
}
