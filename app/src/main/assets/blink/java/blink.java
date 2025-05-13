public class BlinkActivity extends AppCompatActivity {
    ImageView imageView;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blink);

        imageView = findViewById(R.id.imageView);
        animation = AnimationUtils.loadAnimation(this, R.anim.blink);
        imageView.startAnimation(animation);
    }
}