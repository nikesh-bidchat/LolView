package com.bidchat.nik.lolview;

import android.support.v4.content.ContextCompat;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    private ImageView mImageLetterO, mImageSmiley;
    private int mSmileyDispositionHeight = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initiateView();
    }

    public void initiateView() {
        final ViewGroup rootView = (ViewGroup) findViewById(android.R.id.content);

        LinearLayout linearRootView = new LinearLayout(this);
        RelativeLayout.LayoutParams layoutParamsRoot = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        linearRootView.setLayoutParams(layoutParamsRoot);
        linearRootView.setOrientation(LinearLayout.HORIZONTAL);

        // Right Letter L
        ImageView imageLeftLetter = new ImageView(this);
        LinearLayout.LayoutParams layoutParamsLeftLetter = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsLeftLetter.gravity = Gravity.CENTER_VERTICAL;
        layoutParamsLeftLetter.weight = 1;
        imageLeftLetter.setLayoutParams(layoutParamsLeftLetter);
        imageLeftLetter.setAdjustViewBounds(true);
        // ContextCompat.getDrawable(getApplicationContext(), R.mipmap.ic_letter_l);
        imageLeftLetter.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.ic_letter_l));
        linearRootView.addView(imageLeftLetter);

        // Center Relative Holder
        RelativeLayout relativeAnimationHolder = new RelativeLayout(this);
        LinearLayout.LayoutParams layoutParamsAnimationHolder = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParamsAnimationHolder.weight = 1;
        relativeAnimationHolder.setLayoutParams(layoutParamsAnimationHolder);
        // Image to be scaled
        mImageLetterO = new ImageView(this);
        RelativeLayout.LayoutParams layoutParamsLetterOImage = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsLetterOImage.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        mImageLetterO.setLayoutParams(layoutParamsLetterOImage);
        mImageLetterO.setAdjustViewBounds(true);
        mImageLetterO.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_letter_o));
        mImageLetterO.setId(R.id.image_letter_O);
        relativeAnimationHolder.addView(mImageLetterO);
        // Image to be translated
        mImageSmiley = new ImageView(this);
        RelativeLayout.LayoutParams layoutParamsSmiley = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsSmiley.addRule(RelativeLayout.ABOVE, mImageLetterO.getId());
        mImageSmiley.setLayoutParams(layoutParamsSmiley);
        mImageSmiley.setAdjustViewBounds(true);
        mImageSmiley.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_smiley));
        relativeAnimationHolder.addView(mImageSmiley);
        linearRootView.addView(relativeAnimationHolder);
        // Measure the image translation distance
        ViewTreeObserver vtoImageLeftB = mImageSmiley.getViewTreeObserver();
        vtoImageLeftB.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                mImageSmiley.getViewTreeObserver().removeOnPreDrawListener(this);
                mSmileyDispositionHeight = mImageSmiley.getMeasuredHeight();
                startAnimation(mSmileyDispositionHeight);
                return true;
            }
        });

        // Left Letter L
        ImageView imageRightLetter = new ImageView(this);
        LinearLayout.LayoutParams layoutParamsRightLetter = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsRightLetter.gravity = Gravity.CENTER_VERTICAL;
        layoutParamsRightLetter.weight = 1;
        imageRightLetter.setLayoutParams(layoutParamsRightLetter);
        imageRightLetter.setAdjustViewBounds(true);
        imageRightLetter.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.ic_letter_l));
        linearRootView.addView(imageRightLetter);

        rootView.addView(linearRootView);
    }

    public void startAnimation(int translateToPosition) {
        final int ANIMATION_TIME = 500;

        Animation animationTranslate = new TranslateAnimation(0, 0, translateToPosition, 0);//0,0 is the current       coordinates
        animationTranslate.setFillAfter(true); // Needed to keep the result of the animation
        animationTranslate.setDuration(ANIMATION_TIME);
        animationTranslate.setRepeatCount(Animation.INFINITE);
        animationTranslate.setRepeatMode(Animation.REVERSE);
        animationTranslate.setInterpolator(new FastOutLinearInInterpolator());
        mImageSmiley.startAnimation(animationTranslate);// to start animation obviously

        Animation animationScale = new ScaleAnimation(1, 1, 0, 1, Animation.ABSOLUTE, 0,
                Animation.RELATIVE_TO_SELF, 1);
        animationScale.setFillAfter(true); // Needed to keep the result of the animation
        animationScale.setDuration(ANIMATION_TIME);
        animationScale.setRepeatCount(Animation.INFINITE);
        animationScale.setRepeatMode(Animation.REVERSE);
        animationScale.setInterpolator(new FastOutLinearInInterpolator());
        mImageLetterO.startAnimation(animationScale);
    }
}
