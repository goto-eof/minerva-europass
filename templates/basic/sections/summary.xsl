<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">

    <xsl:template name="summary">
        <xsl:param name="root" select="false"/>
        <xsl:param name="title" select="false"/>
        <xsl:param name="topX" select="false"/>
        <xsl:param name="yearsOfExperiencePerTechBackEnd" select="false"/>
        <xsl:param name="yearsOfExperiencePerTechFrontEnd" select="false"/>
        <xsl:param name="topRoles" select="false"/>
        <xsl:param name="yearsExperienceByField" select="false"/>

        <fo:table margin-top="10px" border="1px solid gray" font-size="7pt" space-before="0.5em" space-after="0.5em">

            <fo:table-column/>
            <fo:table-column/>
            <fo:table-column/>
            <fo:table-column/>
            <fo:table-column/>
            <fo:table-column/>
            <fo:table-column/>

            <fo:table-body>
                <fo:table-row>
                    <fo:table-cell background-color="rgb(240,240,240)" number-columns-spanned="7">
                        <fo:block text-align="center" padding="4px">
                            <xsl:value-of select="$title"/>
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>

                <xsl:if test="$root/enableSummaryResultsTechFrequency = 'true'">
                    <xsl:if test="$topX">
                        <xsl:for-each select="$topX/item">
                            <fo:table-row>
                                <fo:table-cell background-color="rgb(240,240,240)" number-columns-spanned="1">
                                    <fo:block padding="4px" margin-left="2px" margin-right="2px">
                                        <xsl:value-of select="key"/>
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding="4px" number-columns-spanned="6">
                                    <fo:block margin-left="10px" text-align="left">
                                        <xsl:value-of select="value"/>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </xsl:for-each>
                    </xsl:if>
                </xsl:if>

                <xsl:if test="$root/enableSummaryResultsTechYearsExperience = 'true'">
                    <xsl:if test="$yearsOfExperiencePerTechBackEnd">
                        <xsl:if test="$yearsOfExperiencePerTechFrontEnd">
                            <fo:table-row>
                                <fo:table-cell background-color="rgb(240,240,240)" number-columns-spanned="1">
                                    <fo:block padding="4px" margin-left="2px" margin-right="2px">
                                        <xsl:value-of
                                                select="$root/translateSummaryYearsExperiencePerSingleBackEndTechnology"/>
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding="4px" number-columns-spanned="6">
                                    <fo:block margin-left="10px" text-align="left">
                                        <xsl:value-of select="$yearsOfExperiencePerTechBackEnd"/>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>

                            <fo:table-row>
                                <fo:table-cell background-color="rgb(240,240,240)" number-columns-spanned="1">
                                    <fo:block padding="4px" margin-left="2px" margin-right="2px">
                                        <xsl:value-of
                                                select="$root/translateSummaryYearsExperiencePerSingleFrontEndTechnology"/>
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding="4px" number-columns-spanned="6">
                                    <fo:block margin-left="10px" text-align="left">
                                        <xsl:value-of select="$yearsOfExperiencePerTechFrontEnd"/>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </xsl:if>
                    </xsl:if>
                </xsl:if>
                <xsl:if test="$topRoles">
                    <xsl:if test="$yearsExperienceByField">

                        <fo:table-row>
                            <fo:table-cell number-columns-spanned="7" border-top="1px solid gray"
                                           border-bottom="1px solid gray">
                                <fo:block>
                                    <fo:table>
                                        <fo:table-column/>
                                        <fo:table-column/>
                                        <fo:table-column/>
                                        <fo:table-column/>
                                        <fo:table-body>
                                            <xsl:if test="$topRoles">
                                                <fo:table-row>
                                                    <xsl:for-each select="$topRoles/item">
                                                        <fo:table-cell background-color="rgb(240,240,240)"
                                                                       number-columns-spanned="1">
                                                            <fo:block padding="4px" margin-left="2px">
                                                                <xsl:value-of select="name"/>
                                                            </fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell padding="4px" number-columns-spanned="1">
                                                            <fo:block margin-left="10px" text-align="left">
                                                                <xsl:value-of select="value"/>
                                                            </fo:block>
                                                        </fo:table-cell>
                                                    </xsl:for-each>
                                                </fo:table-row>
                                            </xsl:if>
                                            <xsl:if test="$yearsExperienceByField">
                                                <fo:table-row>
                                                    <xsl:for-each select="$yearsExperienceByField/item">
                                                        <fo:table-cell padding="4px" background-color="rgb(240,240,240)"
                                                                       number-columns-spanned="1">
                                                            <fo:block margin-left="2px">
                                                                <xsl:value-of select="name"/>
                                                            </fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell padding="4px" number-columns-spanned="1">
                                                            <fo:block margin-left="10px" text-align="left">
                                                                <xsl:value-of select="value"/>
                                                            </fo:block>
                                                        </fo:table-cell>
                                                    </xsl:for-each>
                                                </fo:table-row>
                                            </xsl:if>
                                        </fo:table-body>
                                    </fo:table>
                                </fo:block>
                            </fo:table-cell>
                        </fo:table-row>
                    </xsl:if>
                </xsl:if>

                <fo:table-row>
                    <fo:table-cell number-columns-spanned="7">
                        <fo:block color="gray" margin-left="4px" padding="4px" font-size="7pt">

                            <xsl:value-of select="$root/translateSummaryNote1"/>


                            <xsl:value-of select="' '"/>
                            <xsl:value-of select="$root/generatedOn"/>
                        </fo:block>
                        <fo:block color="gray" margin-left="4px" padding="4px" font-size="7pt">

                            <xsl:value-of select="$root/translateSummaryNote2"/>


                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>

            </fo:table-body>
        </fo:table>
    </xsl:template>
</xsl:stylesheet>