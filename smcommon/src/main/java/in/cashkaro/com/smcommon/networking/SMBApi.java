package in.cashkaro.com.smcommon.networking;

/**
 * Created by techjini on 31/12/15.
 */
public class SMBApi {

    private final String baseHostUrl;

    public SMBApi() {
        this(BuildConfig.HOST);
    }

    public SMBApi(String baseHostUrl) {
        this.baseHostUrl = baseHostUrl;
    }

    public String getBaseHostUrl() {
        return this.baseHostUrl;
    }
}
