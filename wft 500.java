
  wft 500
setvar vendor_container
  if not listexists itemIDs

    createlist itemIDs

else

    clearlist itemIDs

endif 
pushlist itemIDs '1271163463'
pushlist itemIDs '1274335126'
pushlist itemIDs '1205059967'
pushlist itemIDs '1118871940'
pushlist itemIDs '1274335126'
pushlist itemIDs '1122418099'
pushlist itemIDs '1475315860'
pushlist itemIDs '1117601994'
pushlist itemIDs '1271163463'
pushlist itemIDs '1267313805'
pushlist itemIDs '1558589357'
pushlist itemIDs '1223472554'
pushlist itemIDs '1220919130'
pushlist itemIDs '1224954305'
pushlist itemIDs '1818743937'
if not listexists itemPrices

    createlist itemPrices

else

    clearlist itemPrices

endif 
pushlist itemPrices '4000'
pushlist itemPrices '13000'
pushlist itemPrices '26000'
pushlist itemPrices '2500'
pushlist itemPrices '13000'
pushlist itemPrices '27000'
pushlist itemPrices '16000'
pushlist itemPrices '46000'
pushlist itemPrices '4000'
pushlist itemPrices '48000'
pushlist itemPrices '4000'
pushlist itemPrices '36000'
pushlist itemPrices '13500'
pushlist itemPrices '50000'
pushlist itemPrices '3500'
foreach id in itemIDs
    overhead id
    
    wait 250
    
    lift id
    
    wait 250
        
    drop vendor_container 1 0 0

    wait 250

    //here we set price
    pause 500
    if poplist 'itemPrices' front as 'price'
        overhead 'price'
        promptresponse 'price'
    endif

    wait 250
    
    ignore item


endfor
clearignore