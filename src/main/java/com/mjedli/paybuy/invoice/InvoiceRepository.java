/**
 * 
 */
package com.mjedli.paybuy.invoice;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.List;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;
import static java.time.temporal.TemporalAdjusters.lastDayOfYear;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mjedli.paybuy.Tools;
import com.mjedli.paybuy.customer.model.Customer;
import com.mjedli.paybuy.invoice.model.Invoice;
import com.mjedli.paybuy.invoice.model.Result;
import com.mjedli.paybuy.invoice.model.SearchInvoice;

/**
 * @author mjedli
 *
 */
@Repository
public class InvoiceRepository {
	
	@Autowired
	private MongoOperations mongoOperations;

	Tools tools = new Tools();

	public Invoice addInvoice(Invoice invoice) {
		
		ZoneId defaultZoneId = ZoneId.systemDefault();
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'00:00:00.001'Z'");
		LocalDate date = LocalDate.parse(invoice.getDate());
		
		Date startDateIns = Date.from(date.atStartOfDay(defaultZoneId).toInstant());
		
		String startDate = formatter.format(startDateIns);
		
		invoice.setDate(startDate);
		
		return mongoOperations.insert(invoice);
	}

	public List<Invoice> getInvoicesByDate(SearchInvoice searchInvoice) {
		
		ZoneId defaultZoneId = ZoneId.systemDefault();
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'00:00:00.000'Z'");
		LocalDate date = LocalDate.parse(searchInvoice.getStartDate());
		Date startDateIns = Date.from(date.atStartOfDay(defaultZoneId).toInstant());
		String startDate = formatter.format(startDateIns);
		
		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd'T'23:59:59.999'Z'");
		date = LocalDate.parse(searchInvoice.getEndDate());
		Date endDateIns = Date.from(date.atStartOfDay(defaultZoneId).toInstant());
		String endDate = formatter1.format(endDateIns);
		
		Criteria criteria = new Criteria();
		criteria.andOperator(Criteria.where("idCustomer").is(searchInvoice.getIdCustomer()),
				Criteria.where("date").gte(startDate).lt(endDate));
		Query query = new Query(criteria);
		return mongoOperations.find(query, Invoice.class);
	}

	public Invoice getInvoiceById(String id) {
		Query searchQuery = new Query(Criteria.where("id").is(Long.valueOf(id)));
		return mongoOperations.findOne(searchQuery, Invoice.class);
	}

	public List<Invoice> calReslutMonth(Date date) {
		
		ZoneId defaultZoneId = ZoneId.systemDefault();
		
		YearMonth month = YearMonth.from(date.toInstant()
                .atZone(ZoneId.systemDefault()));
		LocalDate start = month.atDay(1);
		LocalDate end   = month.atEndOfMonth();
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Date startDateIns = Date.from(start.atStartOfDay(defaultZoneId).toInstant());
		Date endDateIns = Date.from(end.atStartOfDay(defaultZoneId).toInstant());
		
		String startDate = formatter.format(startDateIns);
		String endDate = formatter.format(endDateIns);
		
		Criteria criteria = new Criteria();
		criteria.andOperator(Criteria.where("date").gte(startDate).lt(endDate));
		Query query = new Query(criteria);
		
		return mongoOperations.find(query, Invoice.class);
		
	}

	public List<Invoice> calReslutYear(Date date) {

		ZoneId defaultZoneId = ZoneId.systemDefault();
		
		LocalDate start = (LocalDate) (date.toInstant()
                .atZone(ZoneId.systemDefault()).toLocalDate()).with(firstDayOfYear()); 
		LocalDate end = (LocalDate) (date.toInstant()
                .atZone(ZoneId.systemDefault()).toLocalDate()).with(lastDayOfYear());
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Date startDateIns = Date.from(start.atStartOfDay(defaultZoneId).toInstant());
		Date endDateIns = Date.from(end.atStartOfDay(defaultZoneId).toInstant());
		
		String startDate = formatter.format(startDateIns);
		String endDate = formatter.format(endDateIns);
		
		Criteria criteria = new Criteria();
		criteria.andOperator(Criteria.where("date").gte(startDate).lt(endDate));
		Query query = new Query(criteria);
			
		return mongoOperations.find(query, Invoice.class);
		
	}

	public List<Invoice> calReslutDay(Date date) {
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'00:00:00.000'Z'");
		String startDate = formatter.format(date);
		
		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd'T'23:59:59.999'Z'");
		String endDate = formatter1.format(date);
		
		Criteria criteria = new Criteria();
		criteria.andOperator(Criteria.where("date").gte(startDate).lt(endDate));
		Query query = new Query(criteria);
		
		return mongoOperations.find(query, Invoice.class);
	}
	
}
