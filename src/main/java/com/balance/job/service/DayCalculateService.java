package com.balance.job.service;

import com.balance.job.dao.DayCalculateDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DayCalculateService {
    @Autowired
    private DayCalculateDao dayCalculateDao;
}
