<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/reservations">
		<items>
			<xsl:for-each select="reservation">
				<booking>
					<xsl:attribute name="location">
						<xsl:value-of select="hotel" />
					</xsl:attribute>
					<xsl:attribute name="paid">
						<xsl:value-of select="totalPayment" />
					</xsl:attribute>
				</booking>
			</xsl:for-each>
		</items>
	</xsl:template>
</xsl:stylesheet>