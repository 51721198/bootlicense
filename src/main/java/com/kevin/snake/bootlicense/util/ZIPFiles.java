package com.kevin.snake.bootlicense.util;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Slf4j
public class ZIPFiles {
    public static boolean compatFiles() {
        boolean processflag = true;
        ZipOutputStream out = null;
        FileInputStream fis = null;
        String path = ClassPathResourceURI.getResourceURI("/").getPath();
        byte[] buffer = new byte[1024];
        File RSAKeyprivate = new File(path + File.separator + FileNames.PRIVATEKEY_NAME);
        File license = new File(path + File.separator + FileNames.LICENSE_FILE_NAME);
        ArrayList<File> list = new ArrayList<File>();
        list.add(RSAKeyprivate);
        list.add(license);

        String strZipName = FileNames.ZIPFILE_NAME;

        try {
            out = new ZipOutputStream(new FileOutputStream(path + strZipName));

            for (File file : list) {
                fis = new FileInputStream(file);
                out.putNextEntry(new ZipEntry(file.getName()));
                int len = 0;
                while ((len = fis.read(buffer)) > 0) {
                    out.write(buffer, 0, len);
                }
            }
        } catch (Exception e) {
            log.error("生成ZIP出现问题:{}", e);
            processflag = false;
            return processflag;
        } finally {
            try {
                out.closeEntry();
                fis.close();
                out.close();
            } catch (IOException e) {
                log.error("exception:{}", e);
            }
        }
        return processflag;
    }
}

