package com.front;

import org.elasticsearch.action.bulk.BulkProcessor.Builder;
import org.elasticsearch.action.bulk.BulkProcessor.Listener;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;

/**
 * A class which helps assist the processing of big data uploads
 * to the search server.
 * @author Nicholas Carugati
 *
 */
public class BulkHandler extends Builder {

	/**
	 * The number of concurrent requests to upload to the server
	 */
	public static final int CONCURRENT_REQUESTS = 10;
	
	/**
	 * The number of bulk actions that should be taken for a single process
	 */
	public static final int BULK_ACTIONS = 10000;
	
	/**
	 * The max size of the bulk before backoff
	 */
	public static final ByteSizeValue BULK_SIZE = new ByteSizeValue(50, ByteSizeUnit.MB);
	
	/**
	 * Instantiates a new Bulk Processor
	 * @param client the client that should be attached to the processor
	 */
	public BulkHandler(Client client) {
		super(client, new Listener() {

			/**
			 * Event handling before the bulk processing 
			 */
			public void beforeBulk(long executionId, BulkRequest request) {
				//For debugging
			}
			
			/**
			 * Event handling after the bulk processing (failed)
			 */
            public void afterBulk(long executionId, BulkRequest request, Throwable failure) {
            	//For debugging
            }
            
    		/**
			 * Event handling before the bulk processing (succeed)
			 */
            public void afterBulk(long executionId, BulkRequest request, BulkResponse response) {
                //For debugging
            }
			
		});
	}

}
