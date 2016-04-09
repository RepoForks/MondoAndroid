package tech.jonas.mondoandroid.api.authentication;

import com.f2prateek.rx.preferences.Preference;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import tech.jonas.mondoandroid.di.scopes.ApiScope;

@ApiScope
public final class OauthInterceptor implements Interceptor {
    private final Preference<String> accessToken;

    @Inject
    public OauthInterceptor(@AccessToken Preference<String> accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();

        if (accessToken.isSet()) {
            builder.header("Authorization", "Bearer " + accessToken.get());
        }

        return chain.proceed(builder.build());
    }
}