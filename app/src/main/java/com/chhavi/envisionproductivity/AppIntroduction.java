package com.chhavi.envisionproductivity;

import android.content.Intent;
import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

/**
 * Created by chhavi on 20/2/16.
 */
public class AppIntroduction extends AppIntro {


    @Override
    public void init(Bundle savedInstanceState) {
        addSlide(AppIntroFragment.newInstance("Title", "asdas'", R.drawable.ic_navigate_next_white_24dp, R.color.colorPrimary));
        addSlide(AppIntroFragment.newInstance("Title", "asdas'", R.drawable.ic_navigate_next_white_24dp, R.color.colorPrimary));
    }

    @Override
    public void onSkipPressed() {

        startActivity(new Intent(AppIntroduction.this, FitnessActivity.class));
        finish();
    }

    @Override
    public void onNextPressed() {

    }

    @Override
    public void onDonePressed() {
        startActivity(new Intent(AppIntroduction.this, FitnessActivity.class));
        finish();
    }

    @Override
    public void onSlideChanged() {

    }
}
