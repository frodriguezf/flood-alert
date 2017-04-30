package ar.com.flood.alerts;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author rabella
 *
 */
public interface AlertRepository extends JpaRepository<AlertEntity, Long>{
	
}

