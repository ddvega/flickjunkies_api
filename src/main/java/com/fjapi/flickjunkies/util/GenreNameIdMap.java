package com.fjapi.flickjunkies.util;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class GenreNameIdMap {
    private static final Map<String, Integer> name2id;
    private static final Map<Integer, String> id2name;

    static {
        name2id = new HashMap<>();
        name2id.put("Action", 28);
        name2id.put("Adventure", 12);
        name2id.put("Animation", 16);
        name2id.put("Comedy", 35);
        name2id.put("Crime", 80);
        name2id.put("Documentary", 99);
        name2id.put("Drama", 18);
        name2id.put("Family", 10751);
        name2id.put("Fantasy", 14);
        name2id.put("History", 36);
        name2id.put("Horror", 27);
        name2id.put("Music", 10402);
        name2id.put("Mystery", 9648);
        name2id.put("Romance", 10749);
        name2id.put("Science Fiction", 878);
        name2id.put("TV Movie", 10770);
        name2id.put("Thriller", 53);
        name2id.put("War", 10752);
        name2id.put("Western", 37);

        id2name = name2id.entrySet().stream().collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
    }

    public static Integer findId(String name) {
        try {
            return name2id.get(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String findName(Integer id) {
        try {
            return id2name.get(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

