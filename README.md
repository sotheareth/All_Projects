# All_Projects
##Create repository for all projects all in one

### KMS this project for test hibernate and include jpa for crud process

- [x] Create criteria with clause statement (the result of criteria is better than SQL Query)
- [x] Create SQL Query (The result of SQL Query can be duplicate)
- [x] 


```

Example<Company> example = Example.of(new Company(companyName));
List<Company> findAll = companyDao.findAll(example); 
Hibernate.initialize(findAll);   //bind composite object immediately to itself
return findAll.get(0);

```


- [x] Create criteria with clause statement (the result of criteria is better than SQL Query)
```
  @Autowired
	private SessionFactory sessionFactory;
	
	public Company findOneByCriteria(Company company) {
		Session session = sessionFactory.getCurrentSession();
		Company result = (Company) session.createCriteria(Company.class)
			.add(Restrictions.like("companyName", company.getCompanyName()))
			.uniqueResult();
		
		return result;
	}
	
	public List<Company> getCompanyByInnerJoin(Company company) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Company.class, "com");
		Criteria userCriteria = criteria.createAlias("users", "u", JoinType.LEFT_OUTER_JOIN, Restrictions.like("u.username", "test1", MatchMode.ANYWHERE));
		userCriteria.add(Restrictions.like("com.companyName", "Dara"));
		
//		List<Object> result = criteria.list();
//		List<Company> companys = new ArrayList<Company>();
//		for(Object item : result){
//			Map map = (Map)item;
//			Company com = (Company)map.get(Criteria.ROOT_ALIAS);
//			List<User> users = (List<User>)map.get("u");
//			com.setUsers(users);
//			companys.add(com);
//		}
		
		return (List<Company>)criteria.list();
	}
```
- the above result if we run unit test
```
  @Transactional
	public void testFindCompanyByInnerJoin() {
		List<Company> company = companyService.getCompanyByInnerJoin(new Company("Dara"));
		Hibernate.initialize(company);
		prettyPrint(company);
		
	}
```
- the result
[ {
  "id" : 1,
  "companyName" : "Dara",
  "age" : 10,
  "users" : null
} ]

- George Washington
- John Adams
- Thomas Jefferson


This site was built using [GitHub Pages](https://pages.github.com/).
