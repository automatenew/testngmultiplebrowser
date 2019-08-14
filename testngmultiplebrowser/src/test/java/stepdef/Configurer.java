package stepdef;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import cucumber.api.TypeRegistry;
import cucumber.api.TypeRegistryConfigurer;
import dataTableObjects.UserDetailsObject;
import dataTableObjects.UserDtls;
import io.cucumber.cucumberexpressions.ParameterByTypeTransformer;
import io.cucumber.datatable.DataTable;
import io.cucumber.datatable.DataTableType;
import io.cucumber.datatable.TableCellByTypeTransformer;
import io.cucumber.datatable.TableEntryByTypeTransformer;
import io.cucumber.datatable.TableTransformer;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.databind.ObjectMapper;

public class Configurer implements TypeRegistryConfigurer {

	
	@Override
	   public void configureTypeRegistry(TypeRegistry registry) {

	      JacksonTableTransformer jacksonTableTransformer = new JacksonTableTransformer();
	      registry.setDefaultParameterTransformer(jacksonTableTransformer);
	        registry.setDefaultDataTableEntryTransformer(jacksonTableTransformer);
	        registry.setDefaultDataTableCellTransformer(jacksonTableTransformer);
	      
	    	  UserDetailsObject userDtlsOnj=new UserDetailsObject();
	    	  registry.defineDataTableType(new DataTableType(UserDtls.class, new TableTransformer<UserDtls>() {
	  			@Override
	  			public UserDtls transform(DataTable table) throws Throwable {
	  				List<UserDetailsObject> lects = table.asMaps().stream().map(m -> userDtlsOnj.userInfo(m)).collect(Collectors.toList());
	  				return new UserDtls(lects);
	  			}
	  		}));
	    	  
	   }

	   @Override
	   public Locale locale() {
	      return Locale.ENGLISH;
	   }

	   private static final class JacksonTableTransformer implements ParameterByTypeTransformer, 
		TableEntryByTypeTransformer, TableCellByTypeTransformer {

		private final ObjectMapper objectMapper = new ObjectMapper();

		@Override
		public Object transform(String s, Type type) {
			return objectMapper.convertValue(s, objectMapper.constructType(type));
		}
		
       @Override
       public <T> T transform(Map<String, String> entry, Class<T> type, TableCellByTypeTransformer cellTransformer) {
           return objectMapper.convertValue(entry, type);
       }

       @Override
       public <T> T transform(String value, Class<T> cellType) {
           return objectMapper.convertValue(value, cellType);
       }
	}
	}