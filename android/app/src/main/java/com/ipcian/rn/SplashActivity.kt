package com.ipcian.rn

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // 스플래시 뷰 참조
        val splashView: View = findViewById(R.id.splash_view)

        // Fade In 애니메이션
        ObjectAnimator.ofFloat(splashView, "alpha", 0f, 1f).apply {
            duration = 1500 // 애니메이션 지속 시간 1.5초
            start() // 애니메이션 시작
        }

        // 딜레이 후 Fade Out 및 화면 전환
        Handler(Looper.getMainLooper()).postDelayed({
            // Fade Out 애니메이션
            ObjectAnimator.ofFloat(splashView, "alpha", 1f, 0f).apply {
                duration = 1500 // 애니메이션 지속 시간 1.5초
                start() // 애니메이션 시작
            }


            // Fade Out이 끝난 후 MainActivity로 전환
            Handler(Looper.getMainLooper()).postDelayed({
                val options = ActivityOptions.makeSceneTransitionAnimation(this@SplashActivity)
                startActivity(Intent(this@SplashActivity, MainActivity::class.java), options.toBundle())
                finish() // 현재 액티비티 닫기
            }, 1500) // Fade Out 애니메이션 시간 (1.5초)
        }, 2000) // 스플래시 화면에서 대기 시간 (2초)
    }
}