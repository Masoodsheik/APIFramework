package context;

public class TestContext {

    private String accessToken;
    private String tokenType; // Typically "bearer"

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getTokenType() {
        return tokenType;
    }

    /**
     * Constructs the Authorization header string (e.g., "Bearer eyJ...").
     */
    public String getAuthorizationHeader() {
        if (tokenType != null && accessToken != null) {
            return tokenType + " " + accessToken;
        }
        // Return null or throw an exception if the token isn't set yet
        return null;
    }
}
