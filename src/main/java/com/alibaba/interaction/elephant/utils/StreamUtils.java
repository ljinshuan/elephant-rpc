package com.alibaba.interaction.elephant.utils;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;

public class StreamUtils {


    public static <T> Stream<T> ofNullable(Collection<T> datas) {


        return Optional.ofNullable(datas).map(d -> d.stream()).orElse(Stream.empty());
    }


}
