package vn.creative.twitterlite.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.codepath.oauth.OAuthLoginActionBarActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.creative.twitterlite.R;
import vn.creative.twitterlite.service.TwitterService;

public class LoginActivity extends OAuthLoginActionBarActivity<TwitterService> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onLoginSuccess() {
        Toast.makeText(this, "Login Twitter success!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, TwitterActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.anim_finish_view_in, R.anim.anim_finish_view_out);
        finish();
    }

    @Override
    public void onLoginFailure(Exception e) {
        Toast.makeText(this, "Fail!", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btn_login_twitter)
    void loginToRest(View view) {
        getClient().connect();
    }
}
