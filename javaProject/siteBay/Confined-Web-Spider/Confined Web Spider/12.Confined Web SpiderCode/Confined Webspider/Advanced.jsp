<%@page language="java" import="java.util.*"%>
<%@include file="Common.jsp"%>
<TABLE width=780 align=center><TR><TD>
<fieldset><legend>Advanced Search Engine</legend><BR>
<table width=600 cellspacing=0 cellpadding=3 align=center style="border:1px solid steelblue" id=advtab><form method=get action="./AdvancedSearch">
<TR><TD>Search Keyword:</TD><Td><input name=quest></td></tr>
<TR><TD>Explore by:</TD><Td><select name=explore>
<option value="any">Any of the Keywords
<option value="and">All Keywords
<option value="exact">Exact Key Phrase
<option value="none">None of the words
</select>
</td></tr>
<TR><TD>Limit Results:</TD><Td><select name=limit>
<option value="5">05 Results per Page
<option value="10">10 Results per Page
<option value="15">15 Results per Page
<option value="20">20 Results per Page
<option value="25">25 Results per Page
</select>
</td></tr>
<TR><TD>Occurence:</TD><Td><select name=occur>
<option value="desc">Description</option>
<option value="url">URL</option>
</select>
</td></tr>
<TR><TD>Highlight Results:</TD><Td><select name=hlightres>
<option value="yes">Required</option>
<option value="no">Not Required</option>
</select>
</td></tr>
<TR><TD colspan=2 align=center><input type=hidden value=0 name=pageno><button type=submit accesskey="S">Search</button>&nbsp;&nbsp;
<button type=reset accesskey="R">Reset</button>
</td></tr>
</form></table><BR></fieldset></TD></TR></TABLE>
<script>setBg();</script>
<SCRIPT>setFooter();</SCRIPT>