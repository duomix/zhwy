package org.aisno.core.mogilefs;

import org.apache.log4j.BasicConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import com.guba.mogilefs.BadHostFormatException;
import com.guba.mogilefs.MogileFS;
import com.guba.mogilefs.NoTrackersException;

@Configuration
@EnableConfigurationProperties({MogileFSProperties.class})
public class MogileFSConfig {
	
	@Autowired
	private MogileFSProperties mogileFSProperties;
	MogileFS mfs = null;
	
	public MogileFS getMogileFS(){
		try {
			BasicConfigurator.configure();
			mfs = new MogileFS(mogileFSProperties.getDomain(),new String[] { mogileFSProperties.getAddressesCsv() }, true);
		} catch (NoTrackersException e) {
			e.printStackTrace();
		} catch (BadHostFormatException e) {
			e.printStackTrace();
		}
		return mfs;
	}
}
