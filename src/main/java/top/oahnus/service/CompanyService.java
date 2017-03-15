package top.oahnus.service;

import top.oahnus.entity.Company;

import java.util.List;

/**
 * Created by oahnus on 2017/2/28.
 */
public interface CompanyService {
    List<Company> insertCompanyByBatch(List<Company> companies);
}
