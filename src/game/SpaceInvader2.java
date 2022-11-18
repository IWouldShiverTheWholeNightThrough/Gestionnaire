package game;

import java.awt.image.BufferedImage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.*;

public class SpaceInvader2 extends Application{

	private Pane root = new Pane();
	private Sprite player = new Sprite(180,550,50,50,"player",Color.MAGENTA);
	private Stage stage;
	private AnimationTimer timer = new AnimationTimer() {
		public void handle(long arg0) {
			update();
		}
	};

	private BooleanProperty zPressed = new SimpleBooleanProperty();
	private BooleanProperty qPressed = new SimpleBooleanProperty();
	private BooleanProperty sPressed = new SimpleBooleanProperty();
	private BooleanProperty dPressed = new SimpleBooleanProperty();


	private double t = 0;
	private int lvl =0;
	private int deadcounter=0;




	private Parent create_scene() throws FileNotFoundException {
		root.setPrefSize(500, 600);

		//		InputStream in = this.getClass().getClassLoader().getResourceAsStream("C:\\Users\\administrateur\\Desktop\\ProjectSp\\playerNuma.png");
		//		Image image = ImageIO.read(new File(in));




		//Image image = new Image(new FileInputStream("C:\\Users\\administrateur\\Desktop\\ProjectSp\\playerNuma.png"));
		Image im = new Image("game/hero.png");


		ImagePattern hero = new ImagePattern(im);
		player.setFill(hero);
		stage.getIcons().add(im);
		root.getChildren().add(player);

		BackgroundImage myBI= new BackgroundImage(new Image("game/tavern.jpg"),
				BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
				BackgroundSize.DEFAULT);
		root.setBackground(new Background(myBI));



		timer.start();

		nextLevel();

		//		Sprite s = new Sprite( 50,50,30,30,"enemy",Color.BLUE);
		//		root.getChildren().add(s);

		return root;
	}

	private void nextLevel() throws FileNotFoundException {
		lvl++;
		deadcounter =0;

		if(lvl==4){
			youWin();
			timer.stop();
		}

		else {
			stage.setTitle("Java Invaders lvl " +lvl);
			if (lvl<3) {
				for (int j = 0; j<lvl;j++) {
					for (int i = 0;i <4;i++) {
						Sprite ennemy = new Sprite(100 + 80*i + 10*j, 100 + 100*j,50,50,"ennemy", Color.BLUE);

						int r = new Random().nextInt(6);
						//Image image = new Image(new FileInputStream("C:\\Users\\administrateur\\Desktop\\ProjectSp\\en_"+ r +".png"));

						Image image = new Image("game/java_"+ r +".png");
						ImagePattern hero = new ImagePattern(image);
						ennemy.setFill(hero);
						root.getChildren().add(ennemy);

					}
				}
			}
			else {
				for (int i = 0;i<4;i++) {

					Sprite ennemy = new Sprite(100 + 80*i, 200 ,50,50,"ennemy", Color.BLUE);
					Image image = new Image("game/souf_" + i + ".png");
					ImagePattern hero = new ImagePattern(image);
					ennemy.setFill(hero);
					root.getChildren().add(ennemy);
				}
			}
		}
	}

	private List <Sprite> sprites(){
		return root.getChildren().stream().map(n -> (Sprite)n).collect(Collectors.toList());
	}

	private void update() {

		t+= 0.016;

		if (player.dead==true) {
			gameOver();
			timer.stop();
		}


		sprites().forEach(s ->{
			switch(s.type) {
			case "ennemybullet":
				s.moveDown();

				if (s.getBoundsInParent().intersects(player.getBoundsInParent())) {
					player.dead=true;
					s.dead=true;


				}

				break;

			case "playerbullet":
				s.moveUp();

				sprites().stream().filter(e -> e.type.equals("ennemy")).forEach(ennemy -> {
					if(s.getBoundsInParent().intersects(ennemy.getBoundsInParent())) {
						
						
						ennemy.dead=true;
						deadcounter++;
						
						
						s.dead=true;
					}

				});

				break;

				


			case "player":
				if(zPressed.get()) {
					s.moveUp();
				}

				if(sPressed.get()){
					s.moveDown();
				}

				if(qPressed.get()){
					s.moveLeft();
				}

				if(dPressed.get()){
					s.moveRight();
				}



				break;

			case "ennemy":
				boolean stop =false;

				if (t>1) {
					if (Math.random() <0.01) {
						shoot(s);
					}

				}

				if (t>1 && t<1.1 && stop ==false ) {
					s.moveLeft();
					stop = true;

				}

				if (t>1.8 && t<1.9 && stop == false ) {
					s.moveDown();
					stop=true;
				}

				if (t>2.5 && t <2.6 && stop == false) {
					s.moveRight();
					stop=true;
				}

				if (t>3 && t <3.1 && stop == false) {
					s.moveRight();
					stop=true;
				}

				if (t>3.6 && t <3.7 && stop == false) {
					s.moveDown();
					stop=true;
				}
				if (t>4.1 && t <4.2 && stop == false) {
					s.moveLeft();
					stop=true;
				}



				break;

			default:
				break;

			}
		});


		root.getChildren().removeIf(n -> {

			Sprite s = (Sprite) n;
			return s.dead;
		});

		if (t>4.2) {
			t=0;
		}


		if (deadcounter==4 && lvl==3 ) {
			try {
				nextLevel();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (deadcounter == lvl*4 || (deadcounter==4 && lvl==3) ) {
			try {
				nextLevel();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}



	}

	private void gameOver() {
		Pane go = new Pane();
		go.setPrefWidth(700);
		go.setPrefHeight(400);
		Image yd = new Image("game/game_over.jpg",900,400,true,false);

		BackgroundImage myBI= new BackgroundImage(yd,
				BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
				BackgroundSize.DEFAULT);
		//then you set to your node

		go.setBackground(new Background(myBI));
		Scene scene2 = new Scene(go);
		stage.setScene(scene2);
		stage.setTitle("T MOR");
		stage.setResizable(true);




	}


	private void youWin() {
		Pane go = new Pane();
		go.setPrefWidth(700);
		go.setPrefHeight(400);
		Image yd = new Image("game/win.png");

		BackgroundImage myBI= new BackgroundImage(yd,
				BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
				BackgroundSize.DEFAULT);
		//then you set to your node

		go.setBackground(new Background(myBI));
		Scene scene2 = new Scene(go,yd.getWidth(),yd.getHeight());
		stage.setScene(scene2);
		stage.setTitle("TRO FOR");
		stage.setResizable(true);

	}

	private void shoot(Sprite who) {

		Sprite s = new Sprite((int) who.getTranslateX() + 18, (int) who.getTranslateY(),
				5,20, who.type + "bullet",Color.YELLOW);

		Image im = new Image("game/error.png");


		ImagePattern hero = new ImagePattern(im);
		s.setFill(hero);

		root.getChildren().add(s);
	}

	public void start(Stage stage) throws Exception {



		//player.setFill(null);

		this.stage = stage;


		Scene scene = new Scene( create_scene());


		scene.setOnKeyPressed(e -> {
			switch (e.getCode()) {
			case Q:
				qPressed.set(true);
				break;

			case D:
				dPressed.set(true);
				break;
			case SPACE:
				shoot(player);
				break;
			default:
				break;
			}


		});

		scene.setOnKeyReleased(e -> {
			switch (e.getCode()) {
			case Q:
				qPressed.set(false);
				break;

			case D:
				dPressed.set(false);
				break;
			default:
				break;
			}


		});






		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();

	}

	private static class Sprite extends Rectangle{
		boolean dead=false;
		final String type;

		Sprite(int x, int y, int w, int h, String type,Color color){
			super(w,h,color);
			this.type = type;
			this.setTranslateX(x);
			this.setTranslateY(y);


		}

		void moveLeft() {
			if (this.getTranslateX()>5) {
				this.setTranslateX(this.getTranslateX() - 5);
			}
		}




		void moveRight() {
			if (this.getTranslateX() < 450) {
				this.setTranslateX(this.getTranslateX() + 5);
			}
		}



		void moveUp() {
			this.setTranslateY(this.getTranslateY() -5);
		}

		void moveDown() {
			this.setTranslateY(this.getTranslateY() +5);
		}



	}





	public static void main(String[] args) {
		launch(args);
	}

}
