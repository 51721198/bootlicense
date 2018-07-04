/*
 * Copyright (c) Mirth Corporation. All rights reserved.
 * 
 * http://www.mirthcorp.com
 * 
 * The software in this package is published under the terms of the MPL license a copy of which has
 * been included with this distribution in the LICENSE.txt file.
 */

package com.kevin.snake.bootlicense.util;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.net.URI;
import java.net.URL;

/**
 * @ClassName: ClassPathResource
 * @Description: 工具类, 根据资源名称获取该资源文件的路径
 * @author: Liu.Dun
 * @date: 2016年7月15日 下午2:52:39
 */
@Slf4j
public class ClassPathResourceURI {

    public static URI getResourceURI(String resource) {
        // If nothing found, null is returned
        URI uri = null;
        try {
            log.debug("Loading: " + resource);
            URL url = ClassPathResourceURI.class.getResource(resource);

            // If nothing is found, try it with or without the '/' in front.
            // Without a '/' in front java prepends the full package name.
            if (url == null) {
                if (resource.charAt(0) == '/') {
                    resource = resource.substring(1);
                } else {
                    resource = "/" + resource;
                }
                url = ClassPathResourceURI.class.getResource(resource);
            }
            if (url != null)
                uri = url.toURI();
        } catch (Exception e) {
            log.error("Could not load resource.", e);
        }
        return uri;
    }

    public static String getWorkspacePath() {
        File file = new File("");
        String absPath = file.getAbsolutePath();
        String workSpacePath = getParentFilePath(absPath);
        return workSpacePath;
    }

    public static String getProjectPath() {
        File file = new File("");
        String projectPath = file.getAbsolutePath();
        return projectPath;
    }

    public static String getParentFilePath(String filePath) {
        int lastIndex = filePath.lastIndexOf("\\");
        if (lastIndex > -1) {
            filePath = filePath.substring(0, lastIndex);
        }
        return filePath;
    }

}

