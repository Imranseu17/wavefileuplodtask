package com.ssl.wavefile.wavefileuplodtask.controller;


import com.ssl.wavefile.wavefileuplodtask.model.Csvfile;
import com.ssl.wavefile.wavefileuplodtask.model.Transaction;
import com.ssl.wavefile.wavefileuplodtask.model.Webfile;
import com.ssl.wavefile.wavefileuplodtask.repository.Csvrepository;
import com.ssl.wavefile.wavefileuplodtask.repository.Transactionrepository;
import com.ssl.wavefile.wavefileuplodtask.repository.Webrepository;
import com.ssl.wavefile.wavefileuplodtask.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class UploadController {

    @Autowired
    Webrepository webrepository;

    @Autowired
    Transactionrepository transactionrepository;

    @Autowired
    Csvrepository csvrepository;

    @Autowired
    StorageService storageService;

    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "/home/imran/Downloads/wavefile/";



    @GetMapping("/")
    public String index() {
        return "upload";
    }

    @PostMapping("/upload")
    public String singleFileUpload(@RequestParam("webfile") MultipartFile webfile,
                                   @RequestParam("csvfile")MultipartFile csvfile,
                                   RedirectAttributes redirectAttributes) {


        Webfile webfileclass = new Webfile();




        if (webfile.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a webfile to upload");
            return "redirect:uploadStatus";
        }

        try {

            byte[] bytes = webfile.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + webfile.getOriginalFilename());
            Files.write(path, bytes);
            storageService.store(webfile);
            webfileclass.setFileName(webfile.getOriginalFilename());
            webrepository.save(webfileclass);
            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + webfile.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }

        File file = new File(csvfile.getOriginalFilename());
        try {
            if(file.exists()){
                file.createNewFile();

                FileOutputStream fos = new FileOutputStream(file);
                fos.write(csvfile.getBytes());
                fos.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            if(file.exists()){
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                String line;

                while ((line = randomAccessFile.readLine()) != null){
                    Csvfile csvfileclass = new Csvfile();
                    Transaction transaction = new Transaction();
                    csvfileclass.setPhoneNumber(line);
                    csvfileclass.setWebfile(webrepository.getOne(webfileclass.getId()));
                    csvrepository.save(csvfileclass);
                    transaction.setCsvfile(csvrepository.getOne(csvfileclass.getId()));
                    transactionrepository.save(transaction);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



        return "redirect:/uploadStatus";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }

}