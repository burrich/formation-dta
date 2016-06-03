package fr.pizzeria.aspect;

import java.util.Calendar;
import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.pizzeria.model.Performance;
import fr.pizzeria.repo.IPerformanceRepository;

@Aspect
@Component
public class PerformanceAspect {
	private static final Logger LOG = Logger.getLogger(PerformanceAspect.class.toString());

	@Autowired private IPerformanceRepository performanceRepository;
	
	@Around("execution(* fr.pizzeria.dao.PizzaDaoDataJpa.findAllPizzas())")
	public Object saveExecTime(ProceedingJoinPoint pjp) throws Throwable {
		long start = System.currentTimeMillis();
        Object obj = pjp.proceed();
        long end = System.currentTimeMillis();

        Performance performance = new Performance();
        performance.setService(pjp.getSignature().toShortString());
        performance.setTempsExecution(end - start);
        performance.setDate(Calendar.getInstance().getTime());
        
        performanceRepository.save(performance);

        return obj;
	}
}
