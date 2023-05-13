package engine.windows;

import java.awt.image.BufferedImage;
import java.util.List;

public class Animation {
    public static final int UPDATE_PER_SECOND = 60;
    float animationTime; //in milliseconds

    AnimationFinishListener animationFinnishListener;

    int frameCount;

    List<BufferedImage> imageList;

    boolean loop;

    boolean finished;

    GameObject gameObject;

    public Animation(
            float animationTime,
            List<BufferedImage> imageList,
            boolean loop,
            AnimationFinishListener animationFinnishListener,
            GameObject gameObject
            ) {
        this.animationTime = animationTime;
        this.imageList = imageList;
        this.frameCount = 0;
        this.loop = loop;
        this.gameObject = gameObject;
        this.animationFinnishListener = animationFinnishListener;
    }

    public BufferedImage getCurrentImage() {
        if (finished) {
            return imageList.get(imageList.size() - 1);
        }

        int frameIndex = (int) (frameCount * (1000f / UPDATE_PER_SECOND) * imageList.size() / animationTime);
        frameCount++;
        if (frameIndex == imageList.size()) {
            if (loop) {
                frameCount = 0;
                frameIndex = 0;
            } else {
                finished = true;
                frameIndex = imageList.size() - 1;
//                gameObject.destroyGameObject();
                this.animationFinnishListener.onAnimationFinished();
            }
        }
        return imageList.get(frameIndex);
    }

    public BufferedImage getCurrentImageSimplified() {
        int frameIndex = (int) (frameCount * (1000f / UPDATE_PER_SECOND) * imageList.size() / animationTime);
        frameCount++;
        if (frameIndex >= imageList.size()) {
            frameIndex = imageList.size() - 1;
        }
        return imageList.get(frameIndex);
    }

    public boolean isLoop() {
        return loop;
    }

    public boolean isFinished() {
        return finished;
    }
}
