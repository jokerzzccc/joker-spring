package com.joker.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * <p>
 * 资源加载顶层接口
 * </p>
 *
 * @author jokerzzccc
 * @date 2022/9/20
 */
public interface Resource {

    InputStream getInputStream() throws IOException;

}
