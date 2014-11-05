package cz.muni.fi.PA165.flight;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.instrument.classloading.LoadTimeWeaver;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;


@Configuration
@EnableTransactionManagement
public class DaoContext {

    @Bean
   	public JpaTransactionManager transactionManager(){
   		return  new JpaTransactionManager(jpaFactoryBean().getObject());
   	}

   	/**
   	 * Starts up a container that emulates behavior prescribed in JPA spec for container-managed EntityManager
   	 * @return jpaFactoryBean
   	 */
   	@Bean
   	public LocalContainerEntityManagerFactoryBean jpaFactoryBean(){
   		LocalContainerEntityManagerFactoryBean jpaFactoryBean = new LocalContainerEntityManagerFactoryBean ();
   		jpaFactoryBean.setDataSource(db());
   		jpaFactoryBean.setLoadTimeWeaver(instrumentationLoadTimeWeaver());
   		jpaFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
   		return jpaFactoryBean;
   	}

   	@Bean
   	public LoadTimeWeaver instrumentationLoadTimeWeaver() {
   		return new InstrumentationLoadTimeWeaver();
   	}

   	@Bean
   	public DataSource db(){
   		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder.setType(EmbeddedDatabaseType.DERBY).build();
   	}
}