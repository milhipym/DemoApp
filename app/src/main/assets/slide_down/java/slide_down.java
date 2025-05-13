public class SlideDownActivity extends AppCompatActivity {
    ImageView imageView;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_down);
        //이건 주석 색깔
        imageView = findViewById(R.id.imageView);
        findViewById(R.id.button).setOnClickListener(v -> {
            animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
            imageView.startAnimation(animation);
        });
    }
}