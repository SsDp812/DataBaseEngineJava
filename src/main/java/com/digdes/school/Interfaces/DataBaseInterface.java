package com.digdes.school.Interfaces;

import java.util.List;
import java.util.Map;

public interface DataBaseInterface {
    public List<Map<String, Object>> execute(String request) throws Exception;
}
