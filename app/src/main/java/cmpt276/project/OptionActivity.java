package cmpt276.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Options Screen
 * Allows users to select an image package
 */
/**
 * <h1>OPTION SCREEN</h1>
 * Allows user to choose board game size and number of bugs for game play.
 * <p>
 * @author  Kaitlynn, Lakshay, Sami, and Yangyang
 * @since   2020-07-08
 */
public class OptionActivity extends AppCompatActivity {

    public static final String SHARED_PREFS_IMAGE_PACK = "shared preferences for image pack";
    public static final String EDITOR_IMAGE_PACK_ID = "id for image pack";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setupImageButton(R.id.imgBtnFruit);
        setupImageButton(R.id.imgBtnVegetable);
        requiredButton(R.id.btnBack);
        requiredButton(R.id.btnReset);
    }

    private void setupImageButton(final int imageId) {
        ImageButton button = findViewById(imageId);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveImagePackId(imageId);
                setupImageButton(R.id.imgBtnFruit);
                setupImageButton(R.id.imgBtnVegetable);
            }
        });

        // Selecting/deselecting image package
        if(getImagePackId(this) == imageId){
            button.setClickable(false);
            button.setForegroundGravity(Gravity.END|Gravity.BOTTOM);
            button.setForeground(getDrawable(R.drawable.drawable_magnifying_glass));
        } else{
            button.setForeground(null);
        }
    }

    private void saveImagePackId(int imagePack) {
        SharedPreferences sharedPreferences = this.getSharedPreferences(SHARED_PREFS_IMAGE_PACK, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(EDITOR_IMAGE_PACK_ID, imagePack);
        editor.apply();
    }

    public static int[] getImagePackArray(Context context){
        int imageButtonId = OptionActivity.getImagePackId(context);
        int[] imagePackArr;
        if(imageButtonId == R.id.imgBtnVegetable){
            imagePackArr = new int[]{R.drawable.broccoli, R.drawable.carrot, R.drawable.eggplant,
                    R.drawable.lettuce, R.drawable.mushroom, R.drawable.onion,
                    R.drawable.radish};
        } else{
            imagePackArr = new int[]{R.drawable.apple, R.drawable.green_apple, R.drawable.lemon,
                    R.drawable.mango, R.drawable.orange, R.drawable.pumpkin,
                    R.drawable.watermelon};
        }
        return imagePackArr;
    }

    private static int getImagePackId(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_IMAGE_PACK, MODE_PRIVATE);
        return sharedPreferences.getInt(EDITOR_IMAGE_PACK_ID, R.id.imgBtnFruit);
    }

    private void requiredButton(final int buttonId) {
        Button btn = findViewById(buttonId);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(R.id.btnBack == buttonId){
                    finish();
                }
                else{
                    // TO BE CONNECTED TO THE SINGLETON
                }
            }
        });
    }

    public static Intent makeIntent(Context context){
        return new Intent(context , OptionActivity.class);
    }
}















