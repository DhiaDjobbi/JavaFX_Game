package sample;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class AudioButtonController {
    public  void play_sound(String sound_path){
        AudioClip voice = new AudioClip(this.getClass().getResource(sound_path).toString());
        voice.play();
    }
    String sound;
    @FXML
    ImageView image;

    @FXML
    Text text;

    public void setAudioLocation(String resourcePath)
    {
        sound = resourcePath;
    }

    public void setImageLocation(String img)
    {
        image.setImage( new Image( "sample/pics/"+img ) );
    }

    public void writeText(String ch)
    {
        text.setText(ch);
        text.setFont(Font.font ("Verdana", 40));
        text.setFill(Color.WHITE);

    }
    public void mousePressed()
        {
            play_sound("voices/"+sound);
        }
}