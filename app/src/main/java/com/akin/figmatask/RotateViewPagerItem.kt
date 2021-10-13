package com.akin.figmatask

import android.view.View
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs

class RotateViewPagerItem :ViewPager2.PageTransformer {
    companion object{
        private var DEFAULT_MAX_ROTATE:Float=15.0f

    }

    private val mMaxRotate = DEFAULT_MAX_ROTATE
    override fun transformPage(view: View, position: Float) {
        val r = 1 - abs(position)
        if (position < -1) {

            view.setRotation(mMaxRotate * -1);
            view.setPivotX(view.getWidth().toFloat());
            view.setPivotY(view.getHeight().toFloat());


        } else if (position <= 1) { // [-1,1]

            if (position < 0)//[0，-1]
            {

                view.setPivotX(view.getWidth() * (0.5f + 0.5f * (-position)));
                view.setPivotY(view.getHeight().toFloat());
                view.setRotation(mMaxRotate * position);

            } else//[1,0]
            {

                view.setPivotX(view.getWidth() * 0.5f * (1 - position));
                view.setPivotY(view.getHeight().toFloat());
                view.setRotation(mMaxRotate * position);
                view.scaleY=0.85f+r *0.25f
            }
        } else { // (1,+Infinity]

            view.setRotation(mMaxRotate);
            view.setPivotX((view.getWidth() * 0).toFloat());
            view.setPivotY(view.getHeight().toFloat());
        }

    }
}