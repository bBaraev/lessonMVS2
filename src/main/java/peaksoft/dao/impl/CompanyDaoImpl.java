package peaksoft.dao.impl;

import org.springframework.stereotype.Repository;
import peaksoft.dao.CompanyDAO;
import peaksoft.entity.Company;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
@Repository
@Transactional
public class CompanyDaoImpl implements CompanyDAO {
    @PersistenceContext
    private EntityManager manager;
    @Override
    public List<Company> getAllCompanies() {
        List<Company>companies=manager.createQuery("from Company",Company.class).getResultList();
        Comparator<Company>comparator=((o1,o2)->(int) (o1.getId()-o2.getId()));
        companies.sort(comparator);
        return companies;
    }

    @Override
    public void addCompany(Company company) {
        manager.persist(company);

    }

    @Override
    public Company getCompanyById(long id) {

        return manager.find(Company.class,id);
    }

    @Override
    public void updateCompany(Company company) {
        manager.merge(company);

    }

    @Override
    public void deleteCompany(Company company) {
        manager.remove(manager.contains(company) ? company : manager.merge(company));

    }
}
