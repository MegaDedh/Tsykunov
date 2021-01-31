package ru.asshands.softwire.tsykunov

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.asshands.softwire.tsykunov.databinding.ActivityMainBinding
import ru.asshands.softwire.tsykunov.ui.start.StartFragment

class MainActivity : AppCompatActivity() {

    private lateinit var bind: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.activity_main_frame_layout, StartFragment())
                    .commit()
        }
    }

}