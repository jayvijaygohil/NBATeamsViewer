package dev.jayvijaygohil.nbateamsviewer.common

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.TimeInterpolator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.view.ViewPropertyAnimator
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import com.google.android.material.animation.AnimationUtils
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HideBottomFabOnScrollBehaviour : FloatingActionButton.Behavior {
    constructor() : super()
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    private var height = 0
    private var currentState = STATE_SCROLLED_UP
    private var currentAnimator: ViewPropertyAnimator? = null

    override fun onLayoutChild(parent: CoordinatorLayout, child: FloatingActionButton, layoutDirection: Int): Boolean {
        val paramsCompat = child.layoutParams as ViewGroup.MarginLayoutParams
        height = child.measuredHeight + paramsCompat.bottomMargin
        return super.onLayoutChild(parent, child, layoutDirection)
    }

    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout, child: FloatingActionButton,
                                     directTargetChild: View, target: View, axes: Int, type: Int): Boolean {
        return type == ViewCompat.TYPE_TOUCH && axes == ViewCompat.SCROLL_AXIS_VERTICAL
    }

    override fun onNestedScroll(coordinatorLayout: CoordinatorLayout, child: FloatingActionButton, target: View,
                                dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int, type: Int,
                                consumed: IntArray) {
        if (currentState != STATE_SCROLLED_DOWN && dyConsumed > 0) {
            slideDown(child)
        } else if (currentState != STATE_SCROLLED_UP && dyConsumed < 0) {
            slideUp(child)
        }
    }

    private fun slideUp(child: FloatingActionButton) {
        if (currentAnimator != null) {
            currentAnimator!!.cancel()
            child.clearAnimation()
        }
        currentState = STATE_SCROLLED_UP
        animateChildTo(
                child, 0, ENTER_ANIMATION_DURATION.toLong(), AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR)
    }

    private fun slideDown(child: FloatingActionButton) {
        if (currentAnimator != null) {
            currentAnimator!!.cancel()
            child.clearAnimation()
        }
        currentState = STATE_SCROLLED_DOWN
        animateChildTo(child, height, EXIT_ANIMATION_DURATION.toLong(), AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR)
    }

    private fun animateChildTo(child: FloatingActionButton, targetY: Int, duration: Long,
                               interpolator: TimeInterpolator) {
        currentAnimator = child
                .animate()
                .translationY(targetY.toFloat())
                .setInterpolator(interpolator)
                .setDuration(duration)
                .setListener(
                        object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator) {
                                currentAnimator = null
                            }
                        })
    }

    companion object {
        private const val ENTER_ANIMATION_DURATION = 225
        private const val EXIT_ANIMATION_DURATION = 225//175

        private const val STATE_SCROLLED_DOWN = 1
        private const val STATE_SCROLLED_UP = 2
    }
}