package xyz.intent.iface;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import org.apache.commons.io.FileUtils;
import xyz.intent.iface.client.ImageClient;

import java.io.File;
import java.io.IOException;

/**
 * @author intent
 */
public class Controller {
    public Button btn2;
    private ImageClient imageClient;

    @FXML
    Button btn1;

    public void onButtonClick(MouseEvent mouseEvent) {
        new Thread(() -> {
            imageClient = new ImageClient("192.168.1.130", 2222);
            imageClient.run();
        }).start();
//        imageClient.requestImage();
//        File file = new File("/Users/intent/Downloads/1.jpg");
//        try {
//            byte[] data = FileUtils.readFileToByteArray(file);
//            BaiduFaceSearchVo searchVo = new BaiduFaceSearchVo();
//            searchVo.setImageType(BaiduApiConst.IMAGE_TYPE_BASE_64);
//            searchVo.setImage(Base64.encodeBase64String(data));
//            searchVo.setGroupIdList(BaiduApiConst.DEFAULT_GROUP);
//            BaiduFaceService.searchUser(BaiduApiConst.TOKEN, searchVo)
//                    .subscribe(body -> {
//                        if (body.getErrorCode() == 0) {
//                            BaiduFaceSearchDto.User user = body.getResult().getUserList().get(0);
//                            System.out.println(user.toString());
//                        }
//                    }, throwable -> {
//                        throwable.printStackTrace();
//                    });
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public void onButton2Click(MouseEvent mouseEvent) {
        if (imageClient != null) {
//            imageClient.requestImage();
            File file = new File("/Users/intent/Downloads/1.jpg");
            try {
                byte[] data = FileUtils.readFileToByteArray(file);
                imageClient.sendData(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void alert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.titleProperty().set(title);
        alert.headerTextProperty().set(message);
        alert.showAndWait();
    }
}
