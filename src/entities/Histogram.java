package entities;

import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.ChartPanel;  

public class Histogram extends JFrame{

	/**
	 * @author Mouaz
	 * 
	 * Histogram for hourly number of requests in the log file
	 */
	private static final long serialVersionUID = 1L;
	private DefaultCategoryDataset dataset = new DefaultCategoryDataset( );

	public Histogram(){
		super("Hourly number of requests");
		
		this.dataset = new DefaultCategoryDataset( );
		
	}
	public void viewHistogram(){
		this.createChart();
		this.setSize(600, 600);
		this.pack();        
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
	    setVisible( true ); 
	    validate();
	}
	public void createChart(){
		 JFreeChart barChart = ChartFactory.createBarChart(
		         "Hourly number of requests",           
		         "Hour",            
		         "Number of requests",            
		         this.dataset,          
		         PlotOrientation.VERTICAL,           
		         true, true, false);
		         
		      ChartPanel chartPanel = new ChartPanel( barChart );        
		      chartPanel.setPreferredSize(new java.awt.Dimension( 560 , 367 ) );        
		      this.setContentPane( chartPanel ); 
		      validate();
	}
	 public void createDataset(int[] hourlyNumberOfRequests) {
		  String hours = "Hours";
		  for(int i =0;i<hourlyNumberOfRequests.length;i++){
			  this.getDataset().addValue( hourlyNumberOfRequests[i], hours , i+"" ); 
		  }       
	   }
	public DefaultCategoryDataset getDataset() {
		return dataset;
	}

	public void setDataset(DefaultCategoryDataset dataset) {
		this.dataset = dataset;
	}  
}
