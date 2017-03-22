package com.bidchat.nik.lolview;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    private ImageView mImageLetterO, mImageSmiley;
    private int mSmileyDispositionHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        User newUser = (User) getIntent().getSerializableExtra(HomeActivity.USER);
        initiateView(newUser, this);
    }

    public void initiateView(User user, final Context context) {
        final ViewGroup rootView = (ViewGroup) findViewById(android.R.id.content);

        RelativeLayout relativeRootView = new RelativeLayout(context);

        RelativeLayout.LayoutParams layoutRootParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        relativeRootView.setLayoutParams(layoutRootParams);

        LinearLayout linearRootView = new LinearLayout(context);
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, getResources().getDisplayMetrics());
        RelativeLayout.LayoutParams layoutRootLinearParams = new RelativeLayout.LayoutParams(px, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutRootLinearParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        linearRootView.setOrientation(LinearLayout.VERTICAL);
        linearRootView.setLayoutParams(layoutRootLinearParams);

        /**     Animation Layer     */
        LinearLayout linearTopView = new LinearLayout(context);
        LinearLayout.LayoutParams layoutTopParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutTopParams.gravity = Gravity.CENTER_VERTICAL;
        linearTopView.setLayoutParams(layoutTopParams);
        linearTopView.setOrientation(LinearLayout.HORIZONTAL);

        // Right Letter L
        ImageView imageLeftLetter = new ImageView(context);
        LinearLayout.LayoutParams layoutParamsLeftLetter = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsLeftLetter.gravity = Gravity.BOTTOM;
        layoutParamsLeftLetter.weight = 1;
        imageLeftLetter.setLayoutParams(layoutParamsLeftLetter);
        imageLeftLetter.setAdjustViewBounds(true);
        imageLeftLetter.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.ic_letter_l));
        linearTopView.addView(imageLeftLetter);

        // Center Relative Holder
        final RelativeLayout relativeAnimationHolder = new RelativeLayout(context);
        LinearLayout.LayoutParams layoutParamsAnimationHolder = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsAnimationHolder.gravity = Gravity.BOTTOM;
        layoutParamsAnimationHolder.weight = 1;
        relativeAnimationHolder.setLayoutParams(layoutParamsAnimationHolder);
        ViewTreeObserver vtoAnimationHolder = relativeAnimationHolder.getViewTreeObserver();
        vtoAnimationHolder.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                relativeAnimationHolder.getViewTreeObserver().removeOnPreDrawListener(this);
                // Image to be scaled
                mImageLetterO = new ImageView(context);
                RelativeLayout.LayoutParams layoutParamsLetterOImage = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParamsLetterOImage.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
                mImageLetterO.setLayoutParams(layoutParamsLetterOImage);
                mImageLetterO.setAdjustViewBounds(true);
                mImageLetterO.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_letter_o));
                mImageLetterO.setId(R.id.image_letter_O);
                relativeAnimationHolder.addView(mImageLetterO);

                // Image to be translated
                mImageSmiley = new ImageView(context);
                RelativeLayout.LayoutParams layoutParamsSmiley = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParamsSmiley.addRule(RelativeLayout.ABOVE, mImageLetterO.getId());
                mImageSmiley.setLayoutParams(layoutParamsSmiley);
                mImageSmiley.setAdjustViewBounds(true);
                mImageSmiley.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_smiley));
                relativeAnimationHolder.addView(mImageSmiley);

                // Measure the image translation distance
                ViewTreeObserver vtoImageLeftB = mImageSmiley.getViewTreeObserver();
                vtoImageLeftB.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                    public boolean onPreDraw() {
                        mImageSmiley.getViewTreeObserver().removeOnPreDrawListener(this);
                        mSmileyDispositionHeight = mImageSmiley.getMeasuredHeight();
                        int heightPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, mSmileyDispositionHeight, getResources().getDisplayMetrics());
                        LinearLayout.LayoutParams layoutParamsAnimationHolder = new LinearLayout.LayoutParams(0, heightPx);
                        layoutParamsAnimationHolder.gravity = Gravity.BOTTOM;
                        layoutParamsAnimationHolder.weight = 1;
                        relativeAnimationHolder.setLayoutParams(layoutParamsAnimationHolder);
                        startAnimation(mSmileyDispositionHeight);
                        return true;
                    }
                });
                return true;
            }
        });
        linearTopView.addView(relativeAnimationHolder);


        // Left Letter L
        ImageView imageRightLetter = new ImageView(context);
        LinearLayout.LayoutParams layoutParamsRightLetter = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsRightLetter.gravity = Gravity.BOTTOM;
        layoutParamsRightLetter.weight = 1;
        imageRightLetter.setLayoutParams(layoutParamsRightLetter);
        imageRightLetter.setAdjustViewBounds(true);
        imageRightLetter.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.ic_letter_l));
        linearTopView.addView(imageRightLetter);

        /**     Profile Layer     */
        LinearLayout linearBottomView = new LinearLayout(context);
        LinearLayout.LayoutParams layoutBottomParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearBottomView.setLayoutParams(layoutBottomParams);
        linearBottomView.setOrientation(LinearLayout.HORIZONTAL);

        int paddingPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics());

        ImageView imageUser = new ImageView(context);
        LinearLayout.LayoutParams layoutParamsImageUser = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        imageUser.setPadding(paddingPx, paddingPx, paddingPx, paddingPx);
        layoutParamsImageUser.weight = 1.5f;
        imageUser.setAdjustViewBounds(true);
        imageUser.setLayoutParams(layoutParamsImageUser);
        Picasso.with(context)
                .load(user.getUserImageUrl())
                .placeholder(R.drawable.progress_animation)
                .error(R.drawable.ic_no_image).noFade().transform(new CircleTransform())
                .into(imageUser);
        imageUser.setOnClickListener(new onProfileClickListener(context, user.getUserId()));
        linearBottomView.addView(imageUser);

        TextView textUserName = new TextView(context);
        LinearLayout.LayoutParams layoutParamsTextUserName = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsTextUserName.gravity = Gravity.CENTER_VERTICAL;
        textUserName.setPadding(paddingPx, paddingPx, paddingPx, paddingPx);
        layoutParamsTextUserName.weight = 3f;
        textUserName.setLayoutParams(layoutParamsTextUserName);
        textUserName.setText(user.getUserName());
        textUserName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        textUserName.setOnClickListener(new onProfileClickListener(context, user.getUserId()));
        linearBottomView.addView(textUserName);

        linearRootView.addView(linearTopView);
        linearRootView.addView(linearBottomView);
        relativeRootView.addView(linearRootView);
        rootView.addView(relativeRootView);
    }

    public void startAnimation(int translateToPosition) {
        final int ANIMATION_TIME = 500;

        Animation animationTranslate = new TranslateAnimation(0, 0, translateToPosition, 0);// 0,0 is the current coordinates
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

    private class onProfileClickListener implements View.OnClickListener {
        Context context;
        int userId;

        private onProfileClickListener(Context context, int userId) {
            this.context = context;
            this.userId = userId;
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(context, "User ID : " + userId, Toast.LENGTH_SHORT).show();
        }
    }
}
