# REST-Assured Workshop - Spotify Demo
This project is an example of how to use OAuth2.0 authentication in REST-Assured

## Description


## Directory Structure
``` 
└───src
    └───test
        ├───java                   
        │   ├───features              # Features 
        │   ├───pages                 
        │   ├───requests              # Requests to API
        │   ├───steps                 # Features implementation
        │   └───utilities              
        │       └───driverFactory
        └───resources       
            └───drivers 
    
```

## Code Execution

### Authentication Information

#### Client id and Client secret
To create a new Client ID and Client Secret go to [Spotify Applications Dashboard](https://developer.spotify.com/dashboard/applications), create a new app and copy from the App Description the Client ID and Client Secret.

#### Properties file
Before executing the code, add a properties file named `properties.properties` in [src/test/resources](src/test/resources) following this structure:
``` 
baseUrl=https://api.spotify.com/v1
tokenUrl=https://accounts.spotify.com/api/token
clientId={your client id}
clientSecret={your client secret}
username={your spotify username/email}
password={your spotify password}
``` 
### Execution
```
mvn clean verify 
``` 
