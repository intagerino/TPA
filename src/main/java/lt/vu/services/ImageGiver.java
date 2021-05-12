package lt.vu.services;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ImageGiver{
    public String giveImage() {
        try{
            Thread.sleep(6000);
        } catch (InterruptedException e){
        }
        return "https://pi.tedcdn.com/r/pf.tedcdn.com/images/playlists/fab_life_of_germs_1541164696.jpg?quality=89&w=256";
    }
}