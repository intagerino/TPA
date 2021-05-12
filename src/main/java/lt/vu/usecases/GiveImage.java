package lt.vu.usecases;

import lt.vu.services.ImageGiver;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Model
@ApplicationScoped
public class GiveImage implements Serializable {

    @Inject
    ImageGiver imageGiver;

    private CompletableFuture<String> imageGivingTask = null;

    @PostConstruct
    public void giveImage(){
        imageGivingTask = CompletableFuture.supplyAsync(() -> imageGiver.giveImage());
    }

    public boolean isImageGiverRunning(){
        return imageGivingTask != null && !imageGivingTask.isDone();
    }

    public String getImageStatus() throws ExecutionException, InterruptedException{
        if(imageGivingTask == null){
            return null;
        } else if (isImageGiverRunning()){
            return "https://www.iconsdb.com/icons/preview/white/square-xxl.png";
        }
        return imageGivingTask.get();
    }

}
